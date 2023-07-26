package com.ljh.querydsl.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * UserVO
 *
 * @author Arsenal
 * created on 2021/3/17 0:44
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVO {
    private String id;
    private String name;
    private String password;
    private String realName;
    private Integer sex;
    private String phone;
}
