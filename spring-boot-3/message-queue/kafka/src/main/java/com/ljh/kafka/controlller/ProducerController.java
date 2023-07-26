package com.ljh.kafka.controlller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ljh.kafka.common.ErrorCode;
import com.ljh.kafka.common.MessageEntity;
import com.ljh.kafka.common.Response;
import com.ljh.kafka.producer.SimpleProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 * ProducerController
 *
 * @author Arsenal
 * created on 2021/3/13 16:15
 */
@Slf4j
@RestController
@RequestMapping("/kafka")
public class ProducerController {

    @Value("${kafka.topic.default}")
    private String topic;
    private final SimpleProducer simpleProducer;
    private final ObjectMapper objectMapper;

    @Autowired
    public ProducerController(SimpleProducer simpleProducer, ObjectMapper objectMapper) {
        this.simpleProducer = simpleProducer;
        this.objectMapper = objectMapper;
    }

    /**
     * <a href="http://127.0.0.1:8200/kafka/hello">GET</a>
     */
    @GetMapping(value = "/hello", produces = {"application/json"})
    public Response sendKafka() {
        return new Response(ErrorCode.SUCCESS, "OK");
    }

    /**
     * <a href="http://127.0.0.1:8200/kafka/hello">POST</a>
     */
    @PostMapping(value = "/send", produces = {"application/json"})
    public Response sendKafka(@RequestBody MessageEntity messageEntity) {
        try {
            log.info("kafka 的消息 = {}", objectMapper.writeValueAsString(messageEntity));
            simpleProducer.send(topic, "key", messageEntity);
            log.info("发送 kafka 成功");
            return new Response(ErrorCode.SUCCESS, "发送 Kafka 成功");
        } catch (Exception e) {
            log.error("发送 kafka 失败", e);
            return new Response(ErrorCode.EXCEPTION, "发送 Kafka 失败");
        }
    }
}
