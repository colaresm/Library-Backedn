package com.library.libraryWDA.validation;

import com.library.libraryWDA.validation.constraint.OfAgeConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class OfAgeValidator implements ConstraintValidator<OfAgeConstraint, LocalDate> {

    @Override
    public void initialize(OfAgeConstraint constraintAnnotation) {
    }

    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
        final int OF_AGE = 18;

        return value != null && ChronoUnit.YEARS.between(value, LocalDate.now()) >= OF_AGE;
    }
}
