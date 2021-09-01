package com.ljh;

import com.ljh.service.JCacheService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;

/**
 * Ehcache Documentation：https://www.ehcache.org/documentation/
 *
 * @author ljh
 * created on 2021/7/13 17:13
 */
@Slf4j
@SpringBootTest
public class JCacheServiceTest {

    private final JCacheService jCacheService;
    private final CacheManager cacheManager;

    @Autowired
    public JCacheServiceTest(JCacheService jCacheService, CacheManager cacheManager) {
        this.jCacheService = jCacheService;
        this.cacheManager = cacheManager;
    }

    @Test
    public void get() {
        System.out.println("CacheManager Type: " + cacheManager.getClass());
        log.info(jCacheService.get(1).toString());
        log.info(jCacheService.get(1).toString());
    }
}
