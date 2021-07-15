package com.ljh;

import com.ljh.service.Ehcache3Service;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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

    @Autowired
    public Ehcache3ServiceTest(Ehcache3Service ehcache3Service) {
        this.ehcache3Service = ehcache3Service;
    }

    @Test
    public void get() {
        log.info(ehcache3Service.get(1).toString());
        log.info(ehcache3Service.get(1).toString());
    }
}
