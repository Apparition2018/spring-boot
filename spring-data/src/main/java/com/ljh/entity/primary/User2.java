package com.ljh.entity.primary;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 * User2 (MongoDB)
 *
 * @author ljh
 * created on 2021/9/2 18:09
 */
@Data
@AllArgsConstructor
public class User2 {

    @Id
    private Long id;
    private String name;
    private Integer age;
}