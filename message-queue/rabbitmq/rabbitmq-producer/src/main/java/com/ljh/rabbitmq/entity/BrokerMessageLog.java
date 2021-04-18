package com.ljh.rabbitmq.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * BrokerMessageLog
 *
 * @author Arsenal
 * created on 2021/4/17 17:53
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BrokerMessageLog {
    
    private String messageId;
    
    private String message;
    
    private Integer tryCount = 0;
    
    private String status;
    
    private Date nextRetry;
    
    private Date createTime;
    
    private Date updateTime;
}
