package com.library.libraryWDA.validation.constraint;

import com.library.libraryWDA.validation.FutureDateValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = FutureDateValidator.class)
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FutureDateConstraint {

    String message() default "Essa data não pode ultrapassar a de hoje.";
    Class[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
