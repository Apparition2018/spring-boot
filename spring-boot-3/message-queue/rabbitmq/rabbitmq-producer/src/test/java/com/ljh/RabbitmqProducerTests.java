package com.ljh;

import com.ljh.constant.MQ;
import com.ljh.entity.Order;
import com.ljh.producer.OrderSender;
import com.ljh.service.OrderService;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

/**
 * RabbitmqProducerTests
 *
 * @author Arsenal
 * created on 2021/4/17 2:00
 */
@SpringBootTest(classes = RabbitmqProducerApplication.class)
public class RabbitmqProducerTests {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private OrderSender orderSender;

    private Order order;

    @BeforeEach
    public void initOrder() {
        order = new Order();
        order.setId(RandomStringUtils.randomNumeric(8));
        order.setName("测试订单" + order.getId());
        order.setMessageId(System.currentTimeMillis() + "$" + UUID.randomUUID());
    }


    @Test
    public void testSendOrder() {
        orderSender.send(order);
    }

    @Autowired
    private OrderService orderService;

    @Test
    public void testOrder2() throws Exception {
        orderService.createOrder(order);
    }

    @Test
    public void testOrder3() {
        rabbitTemplate.convertAndSend(MQ.Exchange.Order3.name, MQ.Routing.KEY3, order);
    }
}
