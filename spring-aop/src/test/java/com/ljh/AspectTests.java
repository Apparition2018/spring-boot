package com.ljh;

import com.ljh.aspect.log.LogService;
import com.ljh.aspect.service.ProductService;
import com.ljh.aspect.service.sub.SubService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * AspectTests
 *
 * @author Arsenal
 * created on 2020/1/2 14:36
 */
@SpringBootTest
public class AspectTests {
    
    private final ProductService productService;
    
    private final SubService subService;
    
    private final LogService logService;

    @Autowired
    public AspectTests(ProductService productService, SubService subService, LogService logService) {
        this.productService = productService;
        this.subService = subService;
        this.logService = logService;
    }
    
    @Test
    public void testException() {
        System.out.println("###begin test###");
        productService.findById(1L);
        productService.findByTwoArgs(1L, "hello");
        productService.getName();
        subService.demo();
        try {
            productService.exDemo();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        logService.log();
    }
}
