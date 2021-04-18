package com.ljh.kafka.producer;

import com.ljh.kafka.common.MessageEntity;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

/**
 * SimpleProducer
 *
 * @author Arsenal
 * created on 2021/3/14 0:54
 */
@Component
public class SimpleProducer {

    private final KafkaTemplate<String, MessageEntity> kafkaTemplate;

    @Autowired
    public SimpleProducer(KafkaTemplate<String, MessageEntity> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
    
    public void send(String topic, MessageEntity messageEntity) {
        kafkaTemplate.send(topic, messageEntity);
    }
    
    public void send(String topic, String key, MessageEntity messageEntity) {
        ProducerRecord<String, MessageEntity> record = new ProducerRecord<>(
                topic, key, messageEntity
        );
        long startTime = System.currentTimeMillis();
        ListenableFuture<SendResult<String, MessageEntity>> future = kafkaTemplate.send(record);
        future.addCallback(new ProducerCallback(startTime, key, messageEntity));
    }
}
