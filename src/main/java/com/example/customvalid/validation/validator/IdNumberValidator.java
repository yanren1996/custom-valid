package com.example.customvalid.validation.validator;

import com.example.customvalid.utils.ValidUtil;
import com.example.customvalid.validation.annotation.IdNumber;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IdNumberValidator implements ConstraintValidator<IdNumber, String> {

    private String[] regex;

    @Override
    public void initialize(IdNumber idNumber) {
        this.regex = idNumber.regex();
    }

    @Override
    public boolean isValid(String id, ConstraintValidatorContext constraintValidatorContext) {
        return ValidUtil.validId(id, regex);
    }

}
