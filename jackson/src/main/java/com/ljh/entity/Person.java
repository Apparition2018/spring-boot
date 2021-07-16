package com.ljh.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

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
    private Dog dog;
    private List<String> hobbies;

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    @Data
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
