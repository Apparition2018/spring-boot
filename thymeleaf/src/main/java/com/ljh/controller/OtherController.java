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
     * th:text & th:utext
     */
    @RequestMapping("utext")
    public String text(Model model) {
        model.addAttribute("message", "<b>Welcome to China!</b>");
        return "other/utext";
    }

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
     * 条件表达式
     */
    @RequestMapping("conditionalExpressions")
    public String conditionalExpressions(Model model) {
        model.addAttribute("x", 99);
        model.addAttribute("isLogin", true);
        model.addAttribute("y", null);
        return "other/conditional-expressions";
    }

    /**
     * 条件判断
     */
    @RequestMapping("conditionalEvaluation")
    public String conditionalEvaluation(Model model) {
        model.addAttribute("num", "0");
        model.addAttribute("str1", "false");
        model.addAttribute("str2", "off");
        model.addAttribute("str3", "no");
        model.addAttribute("str4", "");
        model.addAttribute("obj", null);
        return "other/conditional-evaluation";
    }
}
