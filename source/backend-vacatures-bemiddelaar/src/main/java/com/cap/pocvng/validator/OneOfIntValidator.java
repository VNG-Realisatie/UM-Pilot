package com.cap.pocvng.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.Objects;

/**
 * Custom validator to check if value is oneOf the values given.
 */
public class OneOfIntValidator implements ConstraintValidator<OneOfInt, Integer> {

    private int[] values;

    @Override
    public void initialize(OneOfInt constraintAnnotation) {
        this.values = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(Integer inputValue, ConstraintValidatorContext constraintValidatorContext) {

        if (inputValue == null) {
            return true;
        }

        boolean isValid = false;

        for (Integer value : values) {
            if (Objects.equals(value, inputValue)) {
                isValid = true;
                break;
            }
        }

        if (!isValid) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(
                            inputValue + " must match one of the values in the list: " + Arrays.toString(values)
                    )
                    .addConstraintViolation();
        }
        return isValid;
    }
}
