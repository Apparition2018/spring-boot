package com.ljh;

import com.ljh.entity.Order;
import com.ljh.producer.OrderSender;
import com.ljh.service.OrderService;
import org.junit.jupiter.api.Test;
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
    private OrderSender orderSender;

    @Test
    public void testSend() {
        Order order = new Order();
        order.setId("202104170000000001");
        order.setName("测试订单04171");
        order.setMessageId(System.currentTimeMillis() + "$" + UUID.randomUUID());
        orderSender.send(order);
    }

    @Autowired
    private OrderService orderService;

    @Test
    public void testCreateOrder() throws Exception {
        Order order = new Order();
        order.setId("202104180000000002");
        order.setName("测试订单04181");
        order.setMessageId(System.currentTimeMillis() + "$" + UUID.randomUUID());
        orderService.createOrder(order);
    }
}
