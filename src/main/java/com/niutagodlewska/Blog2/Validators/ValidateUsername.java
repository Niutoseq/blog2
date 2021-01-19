package com.niutagodlewska.Blog2.Validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidateUsername implements ConstraintValidator<ValidateUsernameInterface, String> {

    public void initialize(ValidateUsernameInterface constraint) {
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {
        return username.length()>=3 && username.length()<=20;

    }

}