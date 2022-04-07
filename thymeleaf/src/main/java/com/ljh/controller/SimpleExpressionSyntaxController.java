package com.ljh.controller;

import com.ljh.vo.School;
import com.ljh.vo.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * SimpleExpressionSyntaxController
 *
 * @author ljh
 * created on 2022/4/7 21:22
 */
@Controller
@RequestMapping("expression")
public class SimpleExpressionSyntaxController {

    /**
     * 变量表达式
     */
    @RequestMapping("variable")
    public String variable(Model model) {
        model.addAttribute("name", "张三");
        model.addAttribute("age", 20);

        Student student = new Student()
                .setId(1001)
                .setName("李响")
                .setEmail("LiXiang@qq.com")
                .setAge(26)
                .setSchool(new School("中大", "广州"));
        model.addAttribute(student);
        return "variable";
    }

    /**
     * 链接表达式
     */
    @RequestMapping("message")
    public String message(Model model) {
        return "message";
    }

    /**
     * 链接表达式
     */
    @RequestMapping("linkUrl")
    public String linkUrl(Model model) {
        model.addAttribute("name", "张三");
        return "link-url";
    }
}
