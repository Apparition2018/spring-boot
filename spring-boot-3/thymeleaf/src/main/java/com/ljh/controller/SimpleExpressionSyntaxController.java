package com.ljh.controller;

import com.ljh.vo.School;
import com.ljh.vo.Student;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

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
     * ${...}   变量表达式
     * *{...}   选择变量表达式
     */
    @RequestMapping("variable")
    public String variable(Model model) {
        Student student = new Student()
                .setId(1001)
                .setName("李响")
                .setEmail("LiXiang@qq.com")
                .setAge(26)
                .setSchool(new School("中大", "广州"));
        model.addAttribute(student);
        return "expression/variable";
    }

    /**
     * #{...}   消息表达式
     */
    @RequestMapping("message")
    public String message(Model model) {
        return "expression/message";
    }

    /**
     * `@{...}  链接表达式
     */
    @RequestMapping("linkUrl")
    public String linkUrl(Model model) {
        model.addAttribute("name", "张三");
        return "expression/link-url";
    }

    /**
     * Expression Basic Objects     表达式基本对象
     */
    @RequestMapping("basicObjects")
    public String basicObjects(Model model, HttpSession session) {
        model.addAttribute("requestAttr", "requestVal");
        session.setAttribute("sessionAttr", "sessionVal");
        ServletContext context = session.getServletContext();
        context.setAttribute("contextAttr", "contextVal");
        return "expression/basic-objects";
    }

    /**
     * Expression Utility Objects   表达式功能对象
     */
    @RequestMapping("utilityObjects")
    public String utilityObjects(Model model) {
        model.addAttribute("name", "mary");
        model.addAttribute("date", new Date());
        model.addAttribute("price", 89.35);
        return "expression/utility-objects";
    }
}
