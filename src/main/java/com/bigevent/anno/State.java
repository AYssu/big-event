package com.bigevent.anno;

import com.bigevent.utils.validation.StateValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {StateValidation.class})
public @interface State {
    String message() default "state只允许草稿或者已发布";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};


}
