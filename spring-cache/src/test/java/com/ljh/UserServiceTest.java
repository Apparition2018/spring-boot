package com.ljh;

import com.ljh.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author ljh
 * created on 2021/7/12 17:55
 */
@Slf4j
@SpringBootTest
public class UserServiceTest {

    private final UserService userService;

    @Autowired
    public UserServiceTest(UserService userService) {
        this.userService = userService;
    }

    @Test
    public void cacheable() {
        log.info(userService.get(1).toString());
        // 第一次调用没有缓存，执行了方法，所以输出 into get
        // into get
        // User(id=1, name=姓名1, gender=1)
        log.info(userService.get(1).toString());
        // 第二次调用有缓存，直接返回缓存数据
        // User(id=1, name=姓名1, gender=1)
    }

    @Test
    public void cachePut() {
        log.info(userService.get(1).toString());
        // 第一次调用没有缓存，执行了方法，所以输出 into get
        // into get
        // User(id=1, name=姓名1, gender=1)
        log.info(userService.get(1).toString());
        // 第二次调用有缓存，直接返回缓存数据
        // User(id=1, name=姓名1, gender=1)
    }
}
