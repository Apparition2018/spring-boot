package com.ljh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * OtherController
 *
 * @author ljh
 * created on 2022/4/8 1:02
 */
@Controller
@RequestMapping("other")
public class OtherController {

    /**
     * null 和 空字符串
     */
    @RequestMapping("null")
    public String null_(Model model) {
        model.addAttribute("n1", null);
        model.addAttribute("n2", "");
        return "other/null";
    }

    /**
     * 条件运算符
     */
    @RequestMapping("conditional")
    public String conditional(Model model) {
        model.addAttribute("x", 99);
        model.addAttribute("isLogin", true);
        model.addAttribute("y", null);
        return "other/conditional";
    }
}
