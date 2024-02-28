package com.bigevent.utils.validation;

import com.bigevent.anno.State;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StateValidation implements ConstraintValidator<State,String> {


    /**
     * @param s 将来校验的数据
     * @param constraintValidatorContext
     * @return
     */
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s==null)
        {
            return false;
        }
        if (s.equals("已发布")||s.equals("草稿"))
        {
            return true;
        }
        return false;
    }
}
