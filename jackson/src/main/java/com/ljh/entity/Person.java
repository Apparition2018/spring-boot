package com.ljh.entity;

import com.fasterxml.jackson.annotation.*;
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
// 忽略类不存在的字段 或 忽略指定字段
// @JsonIgnoreProperties(ignoreUnknown = true)
@JsonIgnoreProperties("password")
// 指定序列化顺序
@JsonPropertyOrder({"name", "age", "gender"})
public class Person {

    // 格式化
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss", locale = "zh", timezone = "GMT+8")
    private Date birth;
    private Integer age;
    // 序列化时转换成其它名字
    @JsonProperty("gender")
    private Integer sex;
    // 不参与序列化和反序列化
    @JsonIgnore
    private String password;
    private Dog dog;
    private List<String> hobbies;
    private String name;

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
