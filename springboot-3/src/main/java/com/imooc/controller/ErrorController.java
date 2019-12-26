package com.imooc.controller;

import com.imooc.pojo.LeeJSONResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Arsenal
 * created on 2019/2/20 2:22
 */
@Controller
@RequestMapping("err")
public class ErrorController {

    @RequestMapping("/error")
    public String error() {

        int a = 1 / 0;

        return "thymeleaf/error";
    }

    @RequestMapping("/ajaxerror")
    public String ajaxerror() {

        return "thymeleaf/ajaxerror";
    }

    @RequestMapping("/getAjaxerror")
    @ResponseBody
    public LeeJSONResult getAjaxerror() {

        int a = 1 / 0;
        return LeeJSONResult.ok();
    }
}
