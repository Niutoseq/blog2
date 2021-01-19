package com.niutagodlewska.Blog2.Validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidateAuthor.class)
public @interface ValidateAuthorInterface {

    String message() default "Error in author name, put the username too!";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
