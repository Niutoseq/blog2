package com.niutagodlewska.Blog2.Validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidatePassword implements ConstraintValidator<ValidatePasswordInterface, String> {

    public void initialize(ValidateContentInterface constraint) {
    }

    @Override
    public boolean isValid(String content, ConstraintValidatorContext constraintValidatorContext) {
        //więcej niż 5 bo tyle liter wystarczy żeby napisać super
        return content.length()>=5 && content.length()<=50 && content.matches("[0-9]+");
    }

}