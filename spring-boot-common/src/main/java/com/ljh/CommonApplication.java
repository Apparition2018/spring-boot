package com.ljh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * hibernate
 * jpa
 * mybatis 注解
 * jdbc template
 * <p>
 * nosql: mongodb, memcached
 * <p>
 * zookeeper, dubbo
 * <p>
 * springcloud
 */
@SpringBootApplication
// 扫描 mybatis mapper 包路径
@MapperScan(basePackages = "com.ljh.mapper")
// 扫描所有需要的包，包含一些自用的工具类包所在的路径
@ComponentScan(basePackages = {"com.ljh", "org.n3r.idworker"})
// 开启定时任务
@EnableScheduling
// 开启异步调用方法
@EnableAsync
public class CommonApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommonApplication.class, args);
    }

}
