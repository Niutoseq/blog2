package com.niutagodlewska.Blog2.Validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidateAuthor implements ConstraintValidator<ValidateAuthorInterface, String> {

    public void initialize(ValidateAuthorInterface constraint) {
    }

    @Override
    public boolean isValid(String author, ConstraintValidatorContext constraintValidatorContext) {
        //musi posiadać imię i nazwisko!
        return author.contains(" ");

    }

}