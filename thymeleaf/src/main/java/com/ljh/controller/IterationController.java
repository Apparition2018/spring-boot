package com.ljh.controller;

import com.ljh.vo.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * IterationController
 *
 * @author ljh
 * created on 2022/4/10 2:30
 */
@Controller
public class IterationController {

    @RequestMapping("iteration")
    public String iteration(Model model) {
        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student().setId(1001).setName("刘备").setAge(20));
        studentList.add(new Student().setId(1002).setName("关羽").setAge(21));
        studentList.add(new Student().setId(1003).setName("张飞").setAge(22));
        model.addAttribute("list", studentList);

        Map<String, String> studentMap = new HashMap<>();
        studentMap.put("id", "1001");
        studentMap.put("name", "刘备");
        studentMap.put("age", "20");
        model.addAttribute("map", studentMap);

        List<Map<String, Student>> studentMapList = new ArrayList<>();
        Map<String, Student> map1 = new HashMap<>();
        map1.put("stu-1-1", new Student().setId(1001).setName("刘备").setAge(20));
        Map<String, Student> map2 = new HashMap<>();
        map2.put("stu-2-1", new Student().setId(1002).setName("关羽").setAge(21));
        map2.put("stu-2-2", new Student().setId(1003).setName("张飞").setAge(22));
        studentMapList.add(map1);
        studentMapList.add(map2);
        model.addAttribute("mapList", studentMapList);

        String[] studentNames = new String[]{"刘备", "关羽", "孙权"};
        model.addAttribute("array", studentNames);

        return "iteration";
    }
}
