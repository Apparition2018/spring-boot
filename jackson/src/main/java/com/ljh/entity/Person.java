package com.ljh.entity;

import lombok.Data;

import java.util.Date;

/**
 * Person
 *
 * @author ljh
 * created on 2021/7/15 0:54
 */
@Data
public class Person {

    private String name;
    private Integer age;
    private Date date;
    private int height;
}
