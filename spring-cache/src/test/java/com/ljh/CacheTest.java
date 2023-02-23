package com.ljh;

import com.ljh.service.CacheService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;

import javax.annotation.Resource;

/**
 * CacheTest
 *
 * @author ljh
 * @since 2023/2/23 9:00
 */
@Slf4j
@SpringBootTest
public class CacheTest {

    @Resource(name = CacheService.CACHE_NAME)
    private final CacheService cacheService;
    private final CacheManager cacheManager;

    @Autowired
    public CacheTest(CacheService cacheService, CacheManager cacheManager) {
        this.cacheService = cacheService;
        this.cacheManager = cacheManager;
    }

    @Test
    public void get() {
        System.err.println("CacheManager Type: " + cacheManager.getClass());
        log.info(cacheService.get(1).toString());
        log.info(cacheService.get(1).toString());
    }
}
