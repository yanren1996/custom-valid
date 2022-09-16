package com.example.customvalid.validation.validator;

import com.example.customvalid.utils.ValidUtil;
import com.example.customvalid.validation.annotation.IdNumber;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IdNumberValidator implements ConstraintValidator<IdNumber, String> {

    private ValidUtil.CardType[] cardType;

    @Override
    public void initialize(IdNumber idNumber) {
        this.cardType = idNumber.cardType();
    }

    @Override
    public boolean isValid(String id, ConstraintValidatorContext constraintValidatorContext) {
        return ValidUtil.validId(id, cardType);
    }

}
