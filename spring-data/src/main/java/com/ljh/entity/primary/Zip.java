package com.ljh.entity.primary;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Zip (MongoDB)
 *
 * @author ljh
 * created on 2022/12/2 8:46
 */
@Data
@Document("zip")
public class Zip {
    @Id
    private Integer id;
    private String city;
    private Double[] loc;
    private Integer pop;
    private String state;
}
