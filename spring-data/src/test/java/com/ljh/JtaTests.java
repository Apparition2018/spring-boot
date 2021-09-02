package com.ljh;

import com.ljh.repository.primary.UserRepository;
import com.ljh.repository.secondary.MessageRepository;
import com.ljh.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 使用 JTA 实现多数据源的事务管理：https://blog.didispace.com/spring-boot-learning-24-3-12/
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
