package com.ljh.runner;

import com.ljh.vo.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * InitDataRunner
 *
 * @author ljh
 * created on 2022/11/2 1:01
 */
@Configuration
public class InitDataRunner implements CommandLineRunner {

    private final List<Student> students = new ArrayList<>();

    @Bean("users")
    public List<Student> getUsers() {
        return students;
    }

    @Override
    public void run(String... args) throws Exception {
        students.add(new Student().setId(1001).setName("刘备").setAge(20));
        students.add(new Student().setId(1002).setName("关羽").setAge(21));
        students.add(new Student().setId(1003).setName("张飞").setAge(22));
    }
}
