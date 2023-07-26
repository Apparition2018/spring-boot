package com.ljh.cache.service;

import com.ljh.cache.config.ApplicationContextHolder;
import org.springframework.aop.framework.AopContext;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * MenuService
 *
 * @author Arsenal
 * created on 2020/1/2 23:59
 */
@Component
public class MenuService {

    @Cacheable(cacheNames = {"menu"})
    public List<String> getMenuList() {
        System.out.println("");
        System.out.println("mock: get from db");
        return Arrays.asList("article", "comment", "admin");
    }

    /**
     * 不走缓存
     */
    public List<String> getRecommends() {
        // 类内部调用 getMenuList()，实际上调用的是 this 的 getMenuList()，而不是代理对象的。
        return getMenuList();
    }

    /**
     * 走缓存
     */
    public List<String> getRecommends2() {
        MenuService proxy = (MenuService) AopContext.currentProxy();
        return proxy.getMenuList();
    }

    /**
     * 走缓存
     */
    public List<String> getRecommends3() {
        MenuService proxy = ApplicationContextHolder.getContext().getBean(MenuService.class);
        return proxy.getMenuList();
    }
}
