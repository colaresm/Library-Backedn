package com.library.libraryWDA.validation;

import com.library.libraryWDA.validation.constraint.FutureDateConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class FutureDateValidator implements ConstraintValidator<FutureDateConstraint, LocalDate> {

    @Override
    public void initialize(FutureDateConstraint constraintAnnotation) {
    }

    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
        return value != null && !value.isAfter(LocalDate.now());
    }

}
