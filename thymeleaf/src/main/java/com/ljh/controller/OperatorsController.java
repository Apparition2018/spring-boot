package com.ljh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * OperatorsController
 *
 * @author ljh
 * created on 2022/4/8 0:01
 */
@Controller
@RequestMapping("operators")
public class OperatorsController {

    /**
     * 文本操作
     */
    @RequestMapping("text")
    public String text(Model model) {
        model.addAttribute("info", "java & go");
        model.addAttribute("n1", "张三");
        model.addAttribute("n2", "李四");
        return "operators/text";
    }

    /**
     * 算术运算
     */
    @RequestMapping("arithmetic")
    public String arithmetic(Model model) {
        model.addAttribute("n1", 100);
        model.addAttribute("n2", 20);
        return "operators/arithmetic";
    }

    /**
     * 布尔运算
     * 比较和相等
     */
    @RequestMapping("boolean")
    public String boolean_(Model model) {
        model.addAttribute("married", true);
        model.addAttribute("age", 16);
        model.addAttribute("isLogin", true);
        return "operators/boolean";
    }

    /**
     * No-Operation
     */
    @RequestMapping("no")
    public String no(Model model) {
        model.addAttribute("isLogin", false);
        return "operators/no";
    }
}
