package com.ljh.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * MyApplicationRunner
 *
 * @author ljh
 * created on 2021/7/9 17:38
 */
@Slf4j
@Component
@Order(1)
public class MyApplicationRunner implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.warn("ApplicationRunner");
    }
}
