package com.ljh;

import com.ljh.service.JcacheService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author ljh
 * created on 2021/7/13 17:13
 */
@Slf4j
@SpringBootTest
public class JcacheServiceTest {

    private final JcacheService jcacheService;

    @Autowired
    public JcacheServiceTest(JcacheService jcacheService) {
        this.jcacheService = jcacheService;
    }

    @Test
    public void get() {
        log.info(jcacheService.get(1).toString());
        log.info(jcacheService.get(1).toString());
    }
}
