package com.cap.pocvng.validator;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.Objects;

/**
 * Custom validator to check if value is oneOf the values given.
 */
public class OneOfStringValidator implements ConstraintValidator<OneOfString, String> {

    private String[] values;

    @Override
    public void initialize(OneOfString constraintAnnotation) {
        this.values = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(String inputValue, ConstraintValidatorContext constraintValidatorContext) {

        if (inputValue == null) {
            return true;
        }

        boolean isValid = false;

        for (String value : values) {
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

