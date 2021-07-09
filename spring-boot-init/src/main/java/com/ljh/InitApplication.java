package com.ljh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SpringBoot——启动初始化数据：https://www.jianshu.com/p/01e08aef73c9
 * 顺序：
 * === Initializing Spring embedded WebApplicationContext ===
 * 1. InitializingBean
 * 2. PostConstruct
 * === Tomcat started ===
 * === Started InitApplication ===
 * 3. ApplicationRunner
 * 4. CommandLineRunner
 *
 * @author ljh
 * created on 2021/7/9 17:35
 */
@SpringBootApplication
public class InitApplication {

    public static void main(String[] args) {
        SpringApplication.run(InitApplication.class);
    }
}
