package com.ljh;

import com.ljh.repository.primary.UserRepository;
import com.ljh.repository.secondary.MessageRepository;
import com.ljh.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * <a href="https://blog.didispace.com/spring-boot-learning-24-3-12/">使用 JTA 实现多数据源的事务管理</a>
 * <p><a href="https://blog.csdn.net/qq_33449307/article/details/102550878">springBoot+jpa+jta+atomikos 实现多数据源分布式事务</a>
 *
 * @author ljh
 * created on 2021/8/31 17:41
 */
@SpringBootTest
public class JtaTests {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MessageRepository messageRepository;

    @Test
    public void testJTA() {
        try {
            userService.saveThenThrow();
        } catch (RuntimeException e) {
            Assertions.assertEquals(0, userRepository.findAll().size());
            Assertions.assertEquals(0, messageRepository.findAll().size());
        }
    }
}
