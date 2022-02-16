package com.ljh.producer;

import com.ljh.constant.Constants;
import com.ljh.entity.Order;
import com.ljh.service.BrokerMessageLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * OrderSender
 *
 * @author Arsenal
 * created on 2021/4/17 2:01
 */
@Slf4j
@Component
public class OrderSender {

    private final RabbitTemplate rabbitTemplate;
    private final BrokerMessageLogService brokerMessageLogService;

    public OrderSender(RabbitTemplate rabbitTemplate, BrokerMessageLogService brokerMessageLogService) {
        this.rabbitTemplate = rabbitTemplate;
        this.brokerMessageLogService = brokerMessageLogService;
    }

    public void send(Order order) {
        CorrelationData correlationData = new CorrelationData();
        correlationData.setId(order.getMessageId());
        rabbitTemplate.convertAndSend(
                // exchange
                "order-exchange",
                // routingKey
                // order.* 可模糊匹配 order.001
                // order.# 可模糊匹配 order.001.001 等
                "order.001",
                order,
                correlationData);
    }

    public void sendWithCallback(Order order) throws Exception {
        rabbitTemplate.setConfirmCallback(confirmCallback);
        CorrelationData correlationData = new CorrelationData(order.getMessageId());
        rabbitTemplate.convertAndSend("order-exchange2", "order.002", order, correlationData);
        // 测试失败，exchange 不存在
        // rabbitTemplate.convertAndSend("order-exchange3", "order.003", order, correlationData);
    }

    /**
     * confirm 回调函数
     */
    private final RabbitTemplate.ConfirmCallback confirmCallback = new RabbitTemplate.ConfirmCallback() {
        @Override
        public void confirm(CorrelationData correlationData, boolean ack, String cause) {
            log.info("correlationData: " + correlationData);
            String messageId = Objects.requireNonNull(correlationData).getId();
            if (ack) {
                // 成功则更新状态
                brokerMessageLogService.updateStatusByMessageId(messageId, Constants.ORDER_SEND_SUCCESS);
            } else {
                // 失败则进行具体的后续操作，重试或补偿等手段
                log.info("----------异常处理----------");
            }
        }
    };
}
