package com.ljh.controller;

import com.ljh.vo.Student;
import org.apache.commons.collections.ArrayStack;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

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

    /**
     * 局部变量
     */
    @RequestMapping("localVariables")
    public String localVariables(Model model) {
        model.addAttribute("age", 20);
        return "other/local-variables";
    }

    /**
     * 注释
     * 直接访问        http://localhost:8080/other/comment.html
     * Template 访问  http://localhost:8080/other/comment
     */
    @RequestMapping("comment")
    public String comment(Model model) {
        List<Student> students = new ArrayList<>();
        students.add(new Student().setId(1001).setName("刘备").setAge(20));
        students.add(new Student().setId(1002).setName("关羽").setAge(21));
        students.add(new Student().setId(1003).setName("张飞").setAge(22));
        model.addAttribute("students", students);
        return "other/comment";
    }
}
