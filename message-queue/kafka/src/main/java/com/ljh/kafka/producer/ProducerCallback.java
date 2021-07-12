package com.ljh.kafka.producer;

import com.google.gson.Gson;
import com.ljh.kafka.common.MessageEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFutureCallback;

/**
 * ProducerCallback
 *
 * @author Arsenal
 * created on 2021/3/14 1:00
 */
@Slf4j
public class ProducerCallback implements ListenableFutureCallback<SendResult<String, MessageEntity>> {

    private final Gson gson = new Gson();
    private final long startTime;
    private final String key;
    private final MessageEntity messageEntity;

    public ProducerCallback(long startTime, String key, MessageEntity messageEntity) {
        this.startTime = startTime;
        this.key = key;
        this.messageEntity = messageEntity;
    }

    @Override
    public void onFailure(Throwable throwable) {
        throwable.printStackTrace();
    }

    @Override
    public void onSuccess(SendResult<String, MessageEntity> sendResult) {
        if (sendResult == null) {
            return;
        }
        long elapsedTime = System.currentTimeMillis() - startTime;
        RecordMetadata recordMetadata = sendResult.getRecordMetadata();
        if (recordMetadata != null) {
            String record = "message(" +
                    "key = " + key + "," +
                    " messageEntity = " + gson.toJson(messageEntity) + "," +
                    " sent to partition(" + recordMetadata.partition() + ")" +
                    " with offset(" + recordMetadata.offset() + ")" +
                    " in " + elapsedTime + " ms";
            log.info(record);
        }
    }
} 