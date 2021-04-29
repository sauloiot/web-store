package com.saulo.webstore.services.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ContaInsertValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)

public @interface ContaInsert {
    String message() default "Validation Error";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}