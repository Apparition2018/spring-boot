package com.ljh.producer;

import com.ljh.constant.MQ;
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
                MQ.Exchange.Order.name,
                // routingKey
                // order.* 可模糊匹配 order.001
                // order.# 可模糊匹配 order.001.001 等
                MQ.Routing.KEY,
                order,
                correlationData);
    }

    public void sendWithCallback(Order order) throws Exception {
        rabbitTemplate.setConfirmCallback(confirmCallback);
        CorrelationData correlationData = new CorrelationData(order.getMessageId());
        rabbitTemplate.convertAndSend(MQ.Exchange.Order2.name, MQ.Routing.KEY2, order, correlationData);
        // 测试失败，exchange 不存在
        // rabbitTemplate.convertAndSend("order-exchange4", "order.004", order, correlationData);
    }

    /** confirm 回调函数 */
    private final RabbitTemplate.ConfirmCallback confirmCallback = new RabbitTemplate.ConfirmCallback() {
        @Override
        public void confirm(CorrelationData correlationData, boolean ack, String cause) {
            log.info("correlationData: {}, ack: {}", correlationData, ack);
            String messageId = Objects.requireNonNull(correlationData).getId();
            if (ack) {
                // 成功则更新状态
                brokerMessageLogService.updateStatusByMessageId(messageId, MQ.Status.SEND_SUCCESS);
            } else {
                // 失败则进行具体的后续操作，重试或补偿等手段
                log.info("----- 异常处理 -----");
            }
        }
    };
}
