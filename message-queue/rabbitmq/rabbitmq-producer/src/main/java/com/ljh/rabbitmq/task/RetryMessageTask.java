package com.ljh.rabbitmq.task;

import com.ljh.rabbitmq.constant.Constants;
import com.ljh.rabbitmq.entity.BrokerMessageLog;
import com.ljh.rabbitmq.entity.Order;
import com.ljh.rabbitmq.mapper.BrokerMessageLogMapper;
import com.ljh.rabbitmq.producer.RabbitOrderSender;
import com.ljh.rabbitmq.util.GsonConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    
    private final RabbitOrderSender rabbitOrderSender;
    
    private final BrokerMessageLogMapper brokerMessageLogMapper;

    @Autowired
    public RetryMessageTask(RabbitOrderSender rabbitOrderSender, BrokerMessageLogMapper brokerMessageLogMapper) {
        this.rabbitOrderSender = rabbitOrderSender;
        this.brokerMessageLogMapper = brokerMessageLogMapper;
    }
    
    @Scheduled(initialDelay = 5000, fixedDelay = 10000)
    public void reSend() {
        log.info("----------定时任务开始----------");
        List<BrokerMessageLog> list = brokerMessageLogMapper.query4StatusAndTimeoutMessage();
        list.forEach(brokerMessageLog -> {
            if (brokerMessageLog.getTryCount() >= 3) {
                brokerMessageLogMapper.changeBrokerMessageLogStatus(brokerMessageLog.getMessageId(), Constants.ORDER_SEND_FAILURE);
            } else {
                brokerMessageLogMapper.update4ReSend(brokerMessageLog.getMessageId());
                Order reSendOrder = (Order) GsonConverter.json2Obj(brokerMessageLog.getMessage(), Order.class);
                try {
                    rabbitOrderSender.sendOrder(reSendOrder);
                } catch (Exception e) {
                    e.printStackTrace();
                    log.info("----------异常处理----------");
                }
            }
        });
        
    }
}
