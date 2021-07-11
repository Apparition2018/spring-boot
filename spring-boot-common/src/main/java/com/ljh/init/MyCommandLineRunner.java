package com.ljh.init;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * MyCommandLineRunner
 *
 * @author ljh
 * created on 2021/7/9 17:39
 */
@Slf4j
@Component
@Order(1)
public class MyCommandLineRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        log.warn("CommandLineRunner");
    }
}
