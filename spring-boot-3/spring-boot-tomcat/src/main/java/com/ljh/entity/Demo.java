package com.ljh.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

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
