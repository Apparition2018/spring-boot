package com.ljh.rabbitmq.producer;

import com.ljh.rabbitmq.entity.Order;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * OrderSender
 *
 * @author ljh
 * created on 2021/4/17 2:01
 */
@Component
public class OrderSender {


    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public OrderSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send(Order order) {
        CorrelationData correlationData = new CorrelationData();
        correlationData.setId(order.getMessageId());
        rabbitTemplate.convertAndSend(
                "order-exchange",
                "order.001", // order.* 可以模糊匹配 order.001；order.# 可模糊匹配 order.001.001 等
                order,
                correlationData);
    }
}
