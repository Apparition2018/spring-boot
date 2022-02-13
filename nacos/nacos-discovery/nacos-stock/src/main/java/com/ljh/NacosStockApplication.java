package com.ljh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * NacosStockApplication
 *
 * @author ljh
 * created on 2022/2/14 0:24
 */
@SpringBootApplication
@EnableDiscoveryClient
public class NacosStockApplication {

    public static void main(String[] args) {
        SpringApplication.run(NacosStockApplication.class);
    }
}
