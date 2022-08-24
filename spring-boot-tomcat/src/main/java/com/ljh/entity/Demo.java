package com.ljh.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Demo
 *
 * @author ljh
 * created on 2022/8/24 16:53
 */
@Data
@Entity
public class Demo {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
}
