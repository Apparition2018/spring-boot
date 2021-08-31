package com.ljh;

import com.ljh.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;

/**
 * @author ljh
 * created on 2021/7/14 1:36
 */
@Slf4j
@SpringBootTest
public class RedisServiceTest {

    private final RedisService redisService;
    private final CacheManager cacheManager;

    @Autowired
    public RedisServiceTest(RedisService redisService, CacheManager cacheManager) {
        this.redisService = redisService;
        this.cacheManager = cacheManager;
    }

    @Test
    public void get() {
        System.out.println("CacheManager Type: " + cacheManager.getClass());
        log.info(redisService.get(1).toString());
        log.info(redisService.get(1).toString());
    }
}
