package com.ljh.kafka.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.ljh.kafka.common.MessageEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.support.SendResult;

import java.util.function.BiConsumer;

/**
 * ProducerBiConsumer
 *
 * @author Arsenal
 * created on 2021/3/14 1:00
 */
@Slf4j
public class ProducerBiConsumer implements BiConsumer<SendResult<String, MessageEntity>, Throwable> {

    private final long startTime;
    private final String key;
    private final MessageEntity messageEntity;

    public ProducerBiConsumer(long startTime, String key, MessageEntity messageEntity) {
        this.startTime = startTime;
        this.key = key;
        this.messageEntity = messageEntity;
    }

    @Override
    public void accept(SendResult<String, MessageEntity> sendResult, Throwable throwable) {
        try {
            if (sendResult == null) {
                return;
            }
            String messageEntityStr = JsonMapper.builder().build().writeValueAsString(messageEntity);
            RecordMetadata recordMetadata = sendResult.getRecordMetadata();
            long elapsedTime = System.currentTimeMillis() - startTime;
            if (recordMetadata != null) {
                String record = "message(" +
                        "key = " + key + "," +
                        " messageEntity = " + messageEntityStr + "," +
                        " sent to partition(" + recordMetadata.partition() + ")" +
                        " with offset(" + recordMetadata.offset() + ")" +
                        " in " + elapsedTime + " ms";
                log.info(record);
            }
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
        }
    }
}
