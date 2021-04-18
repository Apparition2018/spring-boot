package com.ljh.rabbitmq.service;

import com.ljh.rabbitmq.constant.Constants;
import com.ljh.rabbitmq.entity.BrokerMessageLog;
import com.ljh.rabbitmq.entity.Order;
import com.ljh.rabbitmq.mapper.BrokerMessageLogMapper;
import com.ljh.rabbitmq.mapper.OrderMapper;
import com.ljh.rabbitmq.producer.RabbitOrderSender;
import com.ljh.rabbitmq.util.GsonConverter;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

    private final RabbitOrderSender rabbitOrderSender;

    @Autowired
    public OrderService(OrderMapper orderMapper, BrokerMessageLogMapper brokerMessageLogMapper, RabbitOrderSender rabbitOrderSender) {
        this.orderMapper = orderMapper;
        this.brokerMessageLogMapper = brokerMessageLogMapper;
        this.rabbitOrderSender = rabbitOrderSender;
    }

    @Transactional
    public void createOrder(Order order) throws Exception {
        orderMapper.insert(order);
        BrokerMessageLog brokerMessageLog = new BrokerMessageLog();
        brokerMessageLog.setMessageId(order.getMessageId());
        brokerMessageLog.setMessage(GsonConverter.obj2Json(order));
        brokerMessageLog.setStatus(Constants.ORDER_SENDING);
        brokerMessageLog.setNextRetry(DateUtils.addMinutes(new Date(), 1));
        brokerMessageLogMapper.insert(brokerMessageLog);
        rabbitOrderSender.sendOrder(order);
    }
}
