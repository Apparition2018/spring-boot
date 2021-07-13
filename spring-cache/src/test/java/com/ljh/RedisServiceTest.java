package com.ljh;

import com.ljh.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author ljh
 * created on 2021/7/14 1:36
 */
@Slf4j
@SpringBootTest
public class RedisServiceTest {

    private final RedisService redisService;

    @Autowired
    public RedisServiceTest(RedisService redisService) {
        this.redisService = redisService;
    }

    @Test
    public void get() {
        log.info(redisService.get(1).toString());
        log.info(redisService.get(1).toString());
    }
}
