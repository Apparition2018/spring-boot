package com.ljh.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * User
 *
 * @author ljh
 * created on 2021/7/12 17:48
 */
@Data
@AllArgsConstructor
public class User implements Serializable {
    private static final long serialVersionUID = 2783638250478469670L;
    private Integer id;
    private String name;
    private String email;
    private Integer gender;
}
