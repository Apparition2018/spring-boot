package com.ljh.cache.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * ApplicationContextHolder
 *
 * @author Arsenal
 * created on 2020/1/3 14:17
 */
@Component
public class ApplicationContextHolder implements ApplicationContextAware {
    
    private static ApplicationContext ctx;
    
    public static ApplicationContext getContext() {
        return ctx;
    }
    
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ctx = applicationContext;
    }
}
