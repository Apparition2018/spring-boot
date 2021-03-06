package com.ljh.exception;

import com.ljh.pojo.LeeJSONResult;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Arsenal
 * created on 2019/2/20 2:30
 */
//@RestControllerAdvice
public class AjaxExceptionHandler {

//    @ExceptionHandler(value = Exception.class)
    public LeeJSONResult defaultErrorHandler(HttpServletRequest req, Exception e) {

        e.printStackTrace();
        return LeeJSONResult.errorException(e.getMessage());
    }

}
