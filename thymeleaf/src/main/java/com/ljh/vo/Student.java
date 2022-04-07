package com.ljh.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Student
 *
 * @author ljh
 * created on 2022/4/7 21:24
 */
@Data
@Accessors(chain = true)
public class Student {
    private Integer id;
    private String name;
    private String email;
    private Integer age;
    private String sex;
    private School school;
}
