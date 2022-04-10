package com.ljh.controller;

import com.ljh.vo.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * InlineController
 *
 * @author ljh
 * created on 2022/4/10 21:25
 */
@Controller
public class InlineController {

    @RequestMapping("inline")
    public String inline(Model model) {
        List<Student> students = new ArrayList<>();
        students.add(new Student().setId(1001).setName("刘备").setAge(20));
        students.add(new Student().setId(1002).setName("关羽").setAge(21));
        students.add(new Student().setId(1003).setName("张飞").setAge(22));
        model.addAttribute("students", students);
        model.addAttribute("name", "三国演义");
        model.addAttribute("info", "<i>四大名著</i>");
        return "inline";
    }
}
