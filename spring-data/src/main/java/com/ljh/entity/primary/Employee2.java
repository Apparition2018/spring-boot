package com.ljh.entity.primary;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

/**
 * Employee2 (MongoDB)
 *
 * @author ljh
 * created on 2022/11/30 17:01
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Document("emp")
public class Employee2 {
    @Id
    private Integer id;
    @Field("username")
    private String name;
    private int age;
    private Double salary;
    private Date birthday;
}
