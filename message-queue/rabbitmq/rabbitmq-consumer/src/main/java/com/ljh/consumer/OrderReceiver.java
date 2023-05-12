package com.ljh.consumer;

import com.ljh.entity.Order;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * OrderReceiver
 *
 * @author Arsenal
 * created on 2021/4/17 2:17
 */
@Slf4j
@Component
public class OrderReceiver {

    @RabbitListener(bindings =
    @QueueBinding(value = @Queue(value = "order-queue", durable = "true"),
            exchange = @Exchange(value = "order-exchange", type = "topic"),
            key = "order.*"))
    @RabbitHandler
    public void onOrderMessage(@Payload Order order, @Headers Map<String, Object> headers, Channel channel) throws IOException {
        log.info("----------收到消息，开始消费----------");
        log.info("订单ID：" + order.getId());
        Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
        channel.basicAck(deliveryTag, false);
    }

    @RabbitListener(bindings =
    @QueueBinding(value = @Queue(value = "${bindings.order.queue.name}", durable = "${bindings.order.queue.durable}"),
            exchange = @Exchange(value = "${bindings.order.exchange.name}", type = "${bindings.order.exchange.type}",
                    ignoreDeclarationExceptions = "${bindings.order.exchange.ignoreDeclarationExceptions}"),
            key = "${bindings.order.key}"))
    @RabbitHandler
    public void onOrderMessage2(Message<Order> message, Channel channel) throws IOException {
        log.info("----------收到消息，开始消费----------");
        log.info("消息ID：" + message.getPayload().getMessageId());
        Object deliveryTagObj = message.getHeaders().get(AmqpHeaders.DELIVERY_TAG);
        if (deliveryTagObj != null) {
            Long deliveryTag = (Long) deliveryTagObj;
            channel.basicAck(deliveryTag, false);
        }
    }
}
