package nz.ac.auckland.lmz.validate

import javax.validation.ConstraintViolation
import javax.validation.Path

/**
 * This class wraps a standard JSR-303 set of {@link ConstraintViolation} objects, and provides operations for
 * manipulating it into more serialisation-friendly formats.
 * <p>Author: <a href="http://gplus.to/tzrlk">Peter Cummuskey</a></p>
 */
public class ValidationResult<BeanType> {

    private Set<ConstraintViolation<BeanType>> errors;

    public ValidationResult(Set<ConstraintViolation<BeanType>> errors) {
        this.errors = errors;
    }

    /**
     * Converts the set of {@link ConstraintViolation}s into a map, where the key is the property path, and the value
     * is the message.
     */
    public Map<String, String> toMap() {
        return errors.collectEntries { ConstraintViolation<BeanType> error ->
            return [ (collapsePath(error.propertyPath)): error.message ];
        }
    }

    /**
     * Converts the set of {@link ConstraintViolation}s into a list of maps, where the property path is stored under a
     * key of 'field', and the message is stored under the 'message' key.
     */
    public List<Map<String, String>> toList() {
        return errors.collect { ConstraintViolation<BeanType> error ->
            return [
                    field: collapsePath(error.propertyPath),
                    message: error.message // should be a code
            ]
        };
    }

    /** Returns whether the validation passed all constraints */
    public boolean isPassed() {
        return errors == null || errors.empty
    }

    /** Turns a validation {@link Path} into a readable String */
    private static String collapsePath(Path path) {
        return path.toList().join('.');
    }
}
