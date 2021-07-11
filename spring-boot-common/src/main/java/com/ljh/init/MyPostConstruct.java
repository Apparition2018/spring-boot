package com.ljh.init;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * MyPostConstruct
 *
 * @author ljh
 * created on 2021/7/9 17:41
 */
@Slf4j
@Component
public class MyPostConstruct {
    @PostConstruct
    public void postConstruct() {
        log.warn("PostConstruct");
    }
}
