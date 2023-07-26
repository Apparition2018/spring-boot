package com.ljh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * RabbitmqProducerApplication
 *
 * @author Arsenal
 * created on 2021/4/17 1:58
 */
@EnableScheduling
@SpringBootApplication
public class RabbitmqProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RabbitmqProducerApplication.class, args);
    }
}
