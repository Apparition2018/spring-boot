package com.ljh;

import com.ljh.entity.primary.User;
import com.ljh.entity.secondary.Message;
import com.ljh.repository.primary.UserRepository;
import com.ljh.repository.secondary.MessageRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * <a href="https://blog.didispace.com/spring-boot-learning-21-3-4/">使用 Spring Data JPA 访问 MySQL</a>
 * <p><a href="https://blog.didispace.com/spring-boot-learning-21-3-8/">Spring Data JPA 的多数据源配置</a>
 *
 * @author ljh
 * created on 2021/8/30 11:50
 */
@SpringBootTest
public class SpringDataJpaTests {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MessageRepository messageRepository;

    @Test
    public void testSpringDataJpa() {
        // 创建10条记录
        userRepository.save(new User("AAA", 10));
        userRepository.save(new User("BBB", 20));
        userRepository.save(new User("CCC", 30));
        userRepository.save(new User("DDD", 40));
        userRepository.save(new User("EEE", 50));
        userRepository.save(new User("FFF", 60));
        userRepository.save(new User("GGG", 70));
        userRepository.save(new User("HHH", 80));
        userRepository.save(new User("III", 90));
        userRepository.save(new User("JJJ", 100));

        System.err.println(userRepository.findAll());

        // findAll()
        Assertions.assertEquals(10, userRepository.findAll().size());

        // findByName()
        Assertions.assertEquals(60, userRepository.findByName("FFF").getAge().longValue());

        // findByNameQuery()
        Assertions.assertEquals(60, userRepository.queryByName("FFF").getAge().longValue());

        // findByNameAndAge()
        Assertions.assertEquals("FFF", userRepository.findByNameAndAge("FFF", 60).getName());

        // delete()
        userRepository.delete(userRepository.findByName("AAA"));
        Assertions.assertEquals(9, userRepository.findAll().size());

        // 第二数据源
        messageRepository.save(new Message("o1", "aaa"));
        messageRepository.save(new Message("o2", "bbb"));
        messageRepository.save(new Message("o3", "ccc"));
        Assertions.assertEquals(3, messageRepository.findAll().size());
    }
}
