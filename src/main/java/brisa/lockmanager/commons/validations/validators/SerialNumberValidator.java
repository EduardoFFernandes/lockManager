package brisa.lockmanager.commons.validations.validators;

import static brisa.lockmanager.commons.constants.MessageConstant.VALIDATION_VALUE_DUPLICATED_ACTIVE_LOCK;

import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;

import brisa.lockmanager.commons.validations.SerialNumberUnique;
import brisa.lockmanager.models.Lock;
import brisa.lockmanager.repositories.LockRepository;
public class SerialNumberValidator extends _BaseValidator implements ConstraintValidator<SerialNumberUnique, Object> {

    private String message;
    private String fieldId;
    private String fieldSerialNumber;

    protected LockRepository repository;

    @Autowired
    public void setLockRepository(final LockRepository repository) {
        this.repository = repository;
    }

    // ---------------------------------------------------------------------------------------------
    // * @see
    // javax.validation.ConstraintValidator#initialize(java.lang.annotation.Annotation)
    // ---------------------------------------------------------------------------------------------
    @Override
    public void initialize(final SerialNumberUnique constraintAnnotation) {
        this.fieldId = constraintAnnotation.fieldId();
        this.fieldSerialNumber = constraintAnnotation.fieldSerialNumber();
        this.message = constraintAnnotation.message();
    }

    // ---------------------------------------------------------------------------------------------
    // * @see javax.validation.ConstraintValidator#isValid(java.lang.Object,
    // javax.validation.ConstraintValidatorContext)
    // ---------------------------------------------------------------------------------------------
    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {

        final BeanWrapperImpl beanWrapper = new BeanWrapperImpl(value);
        final Long id = (Long) beanWrapper.getPropertyValue(this.fieldId);
        final String serialNumber = (String) beanWrapper.getPropertyValue(this.fieldSerialNumber);

        Lock lock = null;
        // If id != null, is an update otherwise is an insert
        if (id != null) {
            final Optional<Lock> lockFound = this.repository.findById(id);
            // Do not validate this at this time, let controller do
            if (!lockFound.isPresent()) {
                return true;
            }
            lock = lockFound.get();
        }

        boolean valid = true;
        if (lock == null || !lock.getSerialNumber().equalsIgnoreCase(serialNumber)) {
            if (this.repository.existsBySerialNumberIgnoreCase(serialNumber)) {
                addFieldMessage(context, this.fieldSerialNumber, VALIDATION_VALUE_DUPLICATED_ACTIVE_LOCK);
                valid = false;
            }
        }

        return valid;
    }

    public String getMessage() {
        return this.message;
    }

}