package com.ljh;

import com.ljh.cache.service.MenuService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * `@Cacheable
 *
 * @author Arsenal
 * created on 2020/1/3 0:01
 */
@SpringBootTest
public class CacheTests {

    @Autowired
    private MenuService menuService;

    @Test
    public void testCache() {
        System.out.println("call: " + menuService.getMenuList());
        System.out.println("call: " + menuService.getMenuList());
    }

    @Test
    public void testInnerCall() {
        System.out.println("call: " + menuService.getRecommends());
        System.out.println("call: " + menuService.getRecommends());

        System.out.println("call: " + menuService.getRecommends2());
        System.out.println("call: " + menuService.getRecommends2());

        System.out.println("call: " + menuService.getRecommends3());
        System.out.println("call: " + menuService.getRecommends3());
    }
}
