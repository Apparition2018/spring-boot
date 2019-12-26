package com.imooc.exception;

import com.imooc.pojo.LeeJSONResult;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Arsenal
 * created on 2019/2/20 2:30
 */
//@RestControllerAdvice
public class IMoocAjaxExceptionHandler {

//    @ExceptionHandler(value = Exception.class)
    public LeeJSONResult defaultErrorHandler(HttpServletRequest req, Exception e) {

        e.printStackTrace();
        return LeeJSONResult.errorException(e.getMessage());
    }

}
