package com.ljh.kafka.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
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

    @KafkaListener(topics = "${kafka.topic.default}", containerFactory = "kafkaListenerContainerFactory")
    public void receive(MessageEntity messageEntity) throws JsonProcessingException {
        log.info(JsonMapper.builder().build().writeValueAsString(messageEntity));
    }
}
