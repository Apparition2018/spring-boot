package com.ljh.controller;

import com.ljh.vo.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * InlineController
 *
 * @author ljh
 * created on 2022/4/10 21:25
 */
@Controller
public class InlineController {

    private final List<Student> students;

    public InlineController(List<Student> students) {
        this.students = students;
    }

    @RequestMapping("inline")
    public String inline(Model model) {
        model.addAttribute("students", students);
        model.addAttribute("name", "三国演义");
        model.addAttribute("info", "<i>四大名著</i>");
        return "inline";
    }
}
