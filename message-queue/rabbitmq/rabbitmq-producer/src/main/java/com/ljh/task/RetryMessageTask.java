package com.ljh.task;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ljh.constant.Constants;
import com.ljh.entity.BrokerMessageLog;
import com.ljh.entity.Order;
import com.ljh.producer.OrderSender;
import com.ljh.service.BrokerMessageLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * RetryMessageTask
 *
 * @author Arsenal
 * created on 2021/4/17 23:21
 */
@Slf4j
@Component
public class RetryMessageTask {

    private final OrderSender orderSender;
    private final BrokerMessageLogService brokerMessageLogService;
    private final ObjectMapper objectMapper;

    public RetryMessageTask(OrderSender orderSender, BrokerMessageLogService brokerMessageLogService, ObjectMapper objectMapper) {
        this.orderSender = orderSender;
        this.brokerMessageLogService = brokerMessageLogService;
        this.objectMapper = objectMapper;
    }

    @Scheduled(initialDelay = 3000, fixedDelay = 10_000)
    public void reSend() {
        log.info("----------定时任务开始----------");
        List<BrokerMessageLog> list = brokerMessageLogService.selectRetry();
        list.forEach(messageLog -> {
            if (messageLog.getTryCount() >= 3) {
                brokerMessageLogService.updateStatusByMessageId(messageLog.getMessageId(), Constants.ORDER_SEND_FAILURE);
            } else {
                brokerMessageLogService.tryCountPlusOne(messageLog.getMessageId());
                try {
                    Order reSendOrder = objectMapper.readValue(messageLog.getMessage(), Order.class);
                    orderSender.sendWithCallback(reSendOrder);
                } catch (Exception e) {
                    e.printStackTrace();
                    log.info("----------异常处理----------");
                }
            }
        });
    }
}
