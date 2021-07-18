package com.ljh.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * Person
 *
 * @author ljh
 * created on 2021/7/15 0:54
 */
@Data
@NoArgsConstructor
public class Person {

    private String name;
    private Integer age;
    private Date birth;
    private Dog dog;
    private List<String> hobbies;

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public Person(String name, Integer age, Date birth) {
        this.name = name;
        this.age = age;
        this.birth = birth;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Dog {
        private String name;
        private Color color;
    }

    public enum Color {
        RED,
        WHITE,
        BLACK;
    }

}
