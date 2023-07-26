package com.ljh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * TomcatApplication
 *
 * @author ljh
 * created on 2022/8/24 16:48
 */
@SpringBootApplication
public class TomcatApplication extends SpringBootServletInitializer {

    /**
     * 使用外部 Tomcat
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(TomcatApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(TomcatApplication.class);
    }
}
