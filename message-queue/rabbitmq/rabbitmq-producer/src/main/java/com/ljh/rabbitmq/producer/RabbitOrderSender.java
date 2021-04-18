package com.ljh.rabbitmq.producer;

import com.ljh.rabbitmq.constant.Constants;
import com.ljh.rabbitmq.entity.Order;
import com.ljh.rabbitmq.mapper.BrokerMessageLogMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * RabbitOrderSender
 *
 * @author Arsenal
 * created on 2021/4/17 22:44
 */
@Slf4j
@Component
public class RabbitOrderSender {

    private final RabbitTemplate rabbitTemplate;

    private final BrokerMessageLogMapper brokerMessageLogMapper;

    @Autowired
    public RabbitOrderSender(RabbitTemplate rabbitTemplate, BrokerMessageLogMapper brokerMessageLogMapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.brokerMessageLogMapper = brokerMessageLogMapper;
    }

    /**
     * 回调函数，confirm 确认
     */
    private final RabbitTemplate.ConfirmCallback confirmCallback = new RabbitTemplate.ConfirmCallback() {
        @Override
        public void confirm(CorrelationData correlationData, boolean ack, String cause) {
            log.info("correlationData: " + correlationData);
            String messageId = Objects.requireNonNull(correlationData).getId();
            if (ack) {
                // 如果 confirm 返回成功，则进行更新
                brokerMessageLogMapper.changeBrokerMessageLogStatus(messageId, Constants.ORDER_SEND_SUCCESS);
            } else {
                // 失败则进行具体的后续操作，重试或补偿等手段
                log.info("异常处理...");
            }
        }
    };

    /**
     * 发送消息方法调用：构建自定义对象消息
     */
    public void sendOrder(Order order) throws Exception {
        // 通过实现 ConfirmCallback 接口，消息发送到 Broker 后出发回调，
        // 确认消息是否到达 Broker 服务器，也就是只确认是否正确到达 Exchange 中
        rabbitTemplate.setConfirmCallback(confirmCallback);
        // 消息ID
        CorrelationData correlationData = new CorrelationData(order.getMessageId());
        rabbitTemplate.convertAndSend("order-exchange3", "order.007", order, correlationData);
    }

}
