package com.company.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

// TODO: Auto-generated Javadoc
public class RangeValidator implements ConstraintValidator<RangeValidation, Integer> {

    private boolean isOptional;
    private int minInclusive;
    private int maxInclusive;

    @Override
    public void initialize(RangeValidation validation) {
        isOptional = validation.isOptional();
        minInclusive = validation.minInclusive();
        maxInclusive = validation.maxInclusive();
    }

    @Override
    public boolean isValid(final Integer value, ConstraintValidatorContext context) {
        boolean result = true;

        if(null == value && isOptional) {
            result = true;
        } else if(null == value && !isOptional) {
            result = false;
        } else {
            result = isMatch(value);
        }

        return result;
    }

    private boolean isMatch(final Integer value) {
        boolean result = false;

        if(value >= minInclusive &&  maxInclusive >= value) {
            result = true;
        }
        return result;
    }
}