package com.example.customvalid.validation.annotation;

import com.example.customvalid.validation.validator.IdNumberValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {IdNumberValidator.class})
public @interface IdNumber {
    String message() default "身分證格式錯誤"; // 預設驗證錯誤訊息

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String[] regex() default {"[A-Z][1-2][0-9]{8}"};
}
