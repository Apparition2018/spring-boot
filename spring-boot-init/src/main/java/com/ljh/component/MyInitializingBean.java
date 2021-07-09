package com.ljh.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * MyInitializingBean
 *
 * @author ljh
 * created on 2021/7/9 17:40
 */
@Slf4j
@Component
public class MyInitializingBean implements InitializingBean {
    @Override
    public void afterPropertiesSet() throws Exception {
        log.warn("InitializingBean");
    }
}
