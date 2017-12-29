package com.kingboy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolationException;

/**
 * @author kingboy--KingBoyWorld@163.com
 * @date 2017/12/29 下午6:59
 * @desc 全局异常捕捉.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    //捕捉方法参数的校验异常
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleValidationException(ConstraintViolationException e) {
        return e.getConstraintViolations().stream()
                .map(message -> message.getInvalidValue() + ":" + message.getMessage() + "<br/>")
                .reduce("", (s0, s1) -> s0 + s1);

    }

    //捕捉方法参数的校验异常
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleValidationException(MethodArgumentNotValidException e) {
        return e.getBindingResult().getAllErrors().stream()
                .map(message -> message.getDefaultMessage() + " ")
                .reduce("", (s0, s1) -> s0 + s1);
    }

}
