package com.ljh.querydsl.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * User
 *
 * @author Arsenal
 * created on 2021/3/16 22:59
 */
@Data
@Entity
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
public class User {
    
    @Id
    @Column(length = 35, unique = true, nullable = false, name = "id")
    private String id;
    @Column(length = 25, unique = true, nullable = false, name = "username")
    private String username;
    @Column(length = 100, nullable = false, name = "password_")
    private String password;
    @Column(length = 40, nullable = false, name = "real_name")
    private String realName;
    @Column(length = 4, name = "sex_")
    private Integer sex;
    @Column(length = 30, name = "phone_")
    private String phone;
}
