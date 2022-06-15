package brisa.lockmanager.commons.validations;

import static brisa.lockmanager.commons.constants.MessageConstant.VALIDATION_VALUE_DUPLICATED;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import brisa.lockmanager.commons.validations.validators.SerialNumberValidator;


@Constraint(validatedBy = SerialNumberValidator.class)
@Target({
        ElementType.TYPE
})
@Retention(RetentionPolicy.RUNTIME)
public @interface SerialNumberUnique {

    String message() default VALIDATION_VALUE_DUPLICATED;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String fieldId();

    String fieldSerialNumber();

}