package com.ljh.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Order
 *
 * @author Arsenal
 * created on 2021/4/17 1:58
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_order")
public class Order implements Serializable {
    private static final long serialVersionUID = 4037830425016588023L;
    @TableId(type = IdType.INPUT)
    private String id;
    private String name;
    /**
     * 消息发送的唯一标识
     */
    private String messageId;
}
