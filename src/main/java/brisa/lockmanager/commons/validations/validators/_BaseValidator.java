package brisa.lockmanager.commons.validations.validators;

import javax.validation.ConstraintValidatorContext;

public class _BaseValidator {

    protected static void addFieldMessage(
            final ConstraintValidatorContext context,
            final String fieldName,
            final String messageTemplate) {

        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(messageTemplate)
                .addPropertyNode(fieldName)
                .addConstraintViolation();
    }
}