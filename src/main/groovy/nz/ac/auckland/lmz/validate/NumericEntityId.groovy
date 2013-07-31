package nz.ac.auckland.lmz.validate

import javax.validation.Constraint
import javax.validation.Payload
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull
import java.lang.annotation.Documented
import java.lang.annotation.Retention
import java.lang.annotation.Target

import static java.lang.annotation.ElementType.*
import static java.lang.annotation.RetentionPolicy.RUNTIME

/**
 * This is a compound constraint that describes all the validations that need to occur to validate an existing entity
 * id, based on a Long value.
 * <p>Author: <a href="http://gplus.to/tzrlk">Peter Cummuskey</a></p>
 */

@Constraint(validatedBy = [])
@NotNull // Can't be unspecified
@Min(1L) // Can't be 0 or less

@Documented
@Target([METHOD, FIELD, ANNOTATION_TYPE])
@Retention(RUNTIME)

public @interface NumericEntityId {

    /** The message to use when this constraint is broken */
    String message() default "{nz.ac.auckland.grayles.validate.existingentityid}";

    /** Validation groups this applies to */
    Class<?>[] groups() default [];

    /** The validation payload */
    Class<? extends Payload>[] payload() default [];

}