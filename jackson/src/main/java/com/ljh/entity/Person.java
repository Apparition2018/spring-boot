package com.ljh.entity;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Person
 *
 * @author ljh
 * created on 2021/7/15 0:54
 */
@Data
@NoArgsConstructor
// 忽略类不存在的字段 或 忽略指定字段
@JsonIgnoreProperties(ignoreUnknown = true)
// @JsonIgnoreProperties("password")
// 指定序列化顺序
@JsonPropertyOrder({"name", "age", "gender"})
// 定义序列化跟属性名，需开启 mapper.enable(SerializationFeature.WRAP_ROOT_VALUE)
@JsonRootName("P")
public class Person {

    // 格式化
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss a", locale = "en", timezone = "GMT+8")
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
    // 反序列化时把未知属性添加到 map
    @JsonAnySetter
    private Map<String, String> other = new HashMap<>();
    // 序列化时把 map 键值对作为对象属性
    @JsonAnyGetter
    public Map<String, String> getOther() {
        return other;
    }

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public Person(String name, Integer age, Date birth) {
        this.name = name;
        this.age = age;
        this.birth = birth;
    }

    public Person(String name, Integer age, Date birth, Integer sex, Dog dog) {
        this.name = name;
        this.age = age;
        this.birth = birth;
        this.sex = sex;
        this.dog = dog;
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
