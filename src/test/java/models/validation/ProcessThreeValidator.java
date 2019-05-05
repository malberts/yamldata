package models.validation;

import models.ProcessThree;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ProcessThreeValidator
        implements ConstraintValidator<ValidProcessThree, ProcessThree> {
    @Override
    public void initialize(ValidProcessThree constraintAnnotation) {
    }

    @Override
    public boolean isValid(ProcessThree scenario, ConstraintValidatorContext context) {
        // Value One or Value Two must be provided, but not both.
        boolean hasValueOne = scenario.getValueOne() != null && !scenario.getValueOne().isEmpty();
        boolean hasValueTwo = scenario.getValueTwo() != null && !scenario.getValueTwo().isEmpty();
        return hasValueOne ^ hasValueTwo;
    }
}
