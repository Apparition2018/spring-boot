package com.ljh;

import com.ljh.service.Ehcache3Service;
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
public class Ehcache3ServiceTest {

    private final Ehcache3Service ehcache3Service;
    private final CacheManager cacheManager;

    @Autowired
    public Ehcache3ServiceTest(Ehcache3Service ehcache3Service, CacheManager cacheManager) {
        this.ehcache3Service = ehcache3Service;
        this.cacheManager = cacheManager;
    }

    @Test
    public void get() {
        System.out.println("CacheManager Type: " + cacheManager.getClass());
        log.info(ehcache3Service.get(1).toString());
        log.info(ehcache3Service.get(1).toString());
    }
}
