package com.ljh.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * User
 *
 * @author ljh
 * created on 2021/7/12 17:48
 */
@Data
@AllArgsConstructor
public class User {

    private Integer id;
    private String name;
    private Integer gender;
}
