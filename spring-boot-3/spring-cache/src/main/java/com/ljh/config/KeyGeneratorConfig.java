package com.ljh.config;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * 默认 Key 生成器: https://docs.spring.io/spring-framework/docs/current/reference/html/integration.html#cache-annotations-cacheable-default-key
 * 自定义 Key 生成器：https://docs.spring.io/spring-framework/docs/current/reference/html/integration.html#cache-annotations-cacheable-key
 *
 * @author ljh
 * created on 2021/7/13 16:35
 */
@Configuration
public class KeyGeneratorConfig {

    @Bean
    public KeyGenerator myKeyGenerator() {
        return (o, method, objects) -> o.getClass().getSimpleName() + "." + method.getName() + Arrays.asList(objects);
    }
}
