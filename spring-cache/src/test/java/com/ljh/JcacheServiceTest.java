package com.ljh;

import com.ljh.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author ljh
 * created on 2021/7/13 17:13
 */
@Slf4j
@SpringBootTest
public class JcacheServiceTest {

    private final UserService userService;

    @Autowired
    public JcacheServiceTest(UserService userService) {
        this.userService = userService;
    }

    @Test
    public void get() {
        log.info(userService.get(1).toString());
        log.info(userService.get(1).toString());
    }
}
