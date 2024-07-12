package org.myProject.crm.dtos.validations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = SsnValidation.class)

public @interface Ssn {
    String message() default "CPF invalido";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
