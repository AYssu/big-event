package com.bigevent.exception;

import com.bigevent.pojo.Result;
import com.mysql.cj.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e)
    {
        e.printStackTrace();
        return Result.error(StringUtils.isNullOrEmpty(e.getMessage()) ? "操作失败" : e.getMessage());
    }
}
