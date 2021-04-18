package com.ljh.rabbitmq;

import com.ljh.rabbitmq.entity.Order;
import com.ljh.rabbitmq.producer.OrderSender;
import com.ljh.rabbitmq.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

/**
 * ApplicationTests
 *
 * @author Arsenal
 * created on 2021/4/17 2:00
 */
@SpringBootTest(classes = ProducerApplication.class)
public class ProducerApplicationTests {

    @Test
    public void contextLoads() {

    }

    @Autowired
    private OrderSender orderSender;

    @Test
    public void testSend1() {
        Order order = new Order();
        order.setId("202104170000000001");
        order.setName("测试订单04171");
        order.setMessageId(System.currentTimeMillis() + "$" + UUID.randomUUID().toString());
        orderSender.send(order);
    }

    @Autowired
    private OrderService orderService;
    
    @Test
    public void testCreateOrder() throws Exception {
        Order order = new Order();
        order.setId("202104180000000002");
        order.setName("测试订单04181");
        order.setMessageId(System.currentTimeMillis() + "$" + UUID.randomUUID().toString());
        orderService.createOrder(order);
    }
}
