package com.library.libraryWDA.validation.constraint;

import com.library.libraryWDA.validation.OfAgeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = OfAgeValidator.class)
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface OfAgeConstraint {

    String message() default "Profissional não pode ter menos de 18 anos.";
    Class[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
