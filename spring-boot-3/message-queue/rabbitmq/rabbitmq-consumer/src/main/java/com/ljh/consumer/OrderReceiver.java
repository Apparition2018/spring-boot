package com.ljh.consumer;

import com.ljh.constant.MQ;
import com.ljh.entity.Order;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.MessageProperties;
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
 * <p><a href="https://docs.spring.io/spring-amqp/reference/html/#async-annotation-driven-enable-signature">@RabbitListener 标注方法接收的参数</a>
 * <pre>
 * 1 The raw org.springframework.amqp.core.Message
 * 2 The MessageProperties from the raw Message
 * 3 The com.rabbitmq.client.Channel on which the message was received
 * 4 The org.springframework.messaging.Message converted from the incoming AMQP message
 * 5 @Header-annotated method arguments to extract a specific header value, including standard AMQP headers.
 * 6 @Headers-annotated argument that must also be assignable to java.util.Map for getting access to all headers.
 * 7 The converted payload
 * </pre>
 *
 * @author Arsenal
 * created on 2021/4/17 2:17
 */
@Slf4j
@Component
public class OrderReceiver {

    @RabbitListener(bindings =
    @QueueBinding(value = @Queue(value = MQ.Queue.Order.name, durable = MQ.Queue.Order.durable),
            exchange = @Exchange(value = MQ.Exchange.Order.name, type = MQ.Exchange.Order.type),
            key = MQ.Bindings.KEY))
    @RabbitHandler
    public void onOrderMessage(@Payload Order order, @Headers Map<String, Object> headers, Channel channel) throws IOException {
        log.info("----- 收到消息，开始消费 -----");
        log.info("订单ID：" + order.getId());
        Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
        channel.basicAck(deliveryTag, false);
    }

    @RabbitListener(bindings =
    @QueueBinding(value = @Queue(value = MQ.Queue.Order2.name, durable = MQ.Queue.Order2.durable),
            exchange = @Exchange(value = MQ.Exchange.Order2.name, type = MQ.Exchange.Order2.type,
                    ignoreDeclarationExceptions = MQ.Exchange.Order2.ignoreDeclarationExceptions),
            key = MQ.Bindings.KEY))
    @RabbitHandler
    public void onOrderMessage2(Message<Order> message, Channel channel) throws IOException {
        log.info("----- 收到消息，开始消费 -----");
        log.info("消息ID：" + message.getPayload().getMessageId());
        Object deliveryTagObj = message.getHeaders().get(AmqpHeaders.DELIVERY_TAG);
        if (deliveryTagObj != null) {
            Long deliveryTag = (Long) deliveryTagObj;
            channel.basicAck(deliveryTag, false);
        }
    }

    @RabbitListener(queues = {MQ.Queue.Order3.name})
    public void onOrderMessage3(Message<Order> message, MessageProperties messageProperties, Channel channel) throws IOException {
        log.info("----- 收到消息，开始消费 -----");
        log.info("消息ID：" + message.getPayload().getMessageId());
        String routingKey = messageProperties.getReceivedRoutingKey();
        if (routingKey.equalsIgnoreCase(MQ.Routing.KEY3)) {
            Object deliveryTagObj = message.getHeaders().get(AmqpHeaders.DELIVERY_TAG);
            if (deliveryTagObj != null) {
                Long deliveryTag = (Long) deliveryTagObj;
                channel.basicAck(deliveryTag, false);
            }
        }
    }
}
