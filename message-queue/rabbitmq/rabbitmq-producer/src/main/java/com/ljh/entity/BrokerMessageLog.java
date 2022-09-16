package com.ljh.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * BrokerMessageLog
 *
 * @author Arsenal
 * created on 2021/4/17 17:53
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BrokerMessageLog {
    @TableId(type = IdType.INPUT)
    private String messageId;
    private String message;
    private Integer tryCount = 0;
    private Integer status;
    private Date nextRetryTime;
    private Date createTime;
    private Date updateTime;
}
