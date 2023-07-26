package com.ljh.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ljh.constant.MQ;
import com.ljh.dao.BrokerMessageLogMapper;
import com.ljh.dao.OrderMapper;
import com.ljh.entity.BrokerMessageLog;
import com.ljh.entity.Order;
import com.ljh.producer.OrderSender;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * OrderService
 *
 * @author Arsenal
 * created on 2021/4/17 23:01
 */
@Service
public class OrderService {

    private final OrderMapper orderMapper;
    private final BrokerMessageLogMapper brokerMessageLogMapper;
    private final OrderSender orderSender;
    private final ObjectMapper objectMapper;

    public OrderService(OrderMapper orderMapper, BrokerMessageLogMapper brokerMessageLogMapper, OrderSender orderSender, ObjectMapper objectMapper) {
        this.orderMapper = orderMapper;
        this.brokerMessageLogMapper = brokerMessageLogMapper;
        this.orderSender = orderSender;
        this.objectMapper = objectMapper;
    }

    @Transactional(rollbackFor = Exception.class)
    public void createOrder(Order order) throws Exception {
        orderMapper.insert(order);
        BrokerMessageLog brokerMessageLog = new BrokerMessageLog();
        brokerMessageLog.setMessageId(order.getMessageId());
        brokerMessageLog.setMessage(objectMapper.writeValueAsString(order));
        brokerMessageLog.setStatus(MQ.Status.SENDING);
        brokerMessageLog.setNextRetryTime(DateUtils.addSeconds(new Date(), 10));
        brokerMessageLogMapper.insert(brokerMessageLog);
        orderSender.sendWithCallback(order);
    }
}
