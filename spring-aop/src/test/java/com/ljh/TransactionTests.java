package com.ljh;

import com.ljh.transaction.service.DemoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * TransactionTests
 *
 * @author Arsenal
 * created on 2020/1/2 17:57
 */
@SpringBootTest
public class TransactionTests {
    
    private final DemoService demoService;

    @Autowired
    public TransactionTests(DemoService demoService) {
        this.demoService = demoService;
    }
    
    @Test
    public void testWithoutTransaction() {
        demoService.addUser("tom");
    }
}
