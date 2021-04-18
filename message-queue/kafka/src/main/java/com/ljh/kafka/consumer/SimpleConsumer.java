package com.ljh.kafka.consumer;

import com.google.gson.Gson;
import com.ljh.kafka.common.MessageEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * SimpleConsumer
 *
 * @author Arsenal
 * created on 2021/3/14 1:09
 */
@Slf4j
@Component
public class SimpleConsumer {

    private final Gson gson = new Gson();

    @KafkaListener(topics = "${kafka.topic.default}", containerFactory = "kafkaListenerContainerFactory")
    public void receive(MessageEntity messageEntity) {
        log.info(gson.toJson(messageEntity));
    }
}
