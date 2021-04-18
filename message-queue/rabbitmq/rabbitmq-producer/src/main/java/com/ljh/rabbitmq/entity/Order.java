package com.ljh.rabbitmq.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * Order
 *
 * @author Arsenal
 * created on 2021/4/17 1:58
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order implements Serializable {

    private static final long serialVersionUID = 4037830425016588023L;

    private String id;
    private String name;
    /**
     * 存储消息发送的唯一标识
     */
    private String messageId;
}
