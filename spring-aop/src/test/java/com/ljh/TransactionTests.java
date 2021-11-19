package com.ljh;

import com.ljh.transaction.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * `@Transactional
 *
 * @author Arsenal
 * created on 2020/1/2 17:57
 */
@SpringBootTest
public class TransactionTests {

    @Autowired
    private UserService userService;

    @Test
    public void testWithoutTransaction() {
        userService.addUser("tom");
        userService.addUser("tom");
    }
}
