package com.ljh;

import com.ljh.entity.primary.User2;
import com.ljh.repository.primary.UserMongoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 使用 MongoDB：https://blog.didispace.com/spring-boot-learning-24-6-1/
 *
 * @author ljh
 * created on 2021/9/2 17:34
 */
@SpringBootTest
public class MongodbTests {

    @Autowired
    private UserMongoRepository userMongoRepository;

    @Test
    public void testMongodb() {
        userMongoRepository.deleteAll();

        // 创建数据，并验证总数
        userMongoRepository.save(new User2(1L, "didi", 30));
        userMongoRepository.save(new User2(2L, "mama", 40));
        userMongoRepository.save(new User2(3L, "kaka", 50));
        Assertions.assertEquals(3, userMongoRepository.findAll().size());

        // 删除一个User，再验证User总数
        User2 user = userMongoRepository.findById(1L).get();
        userMongoRepository.delete(user);
        Assertions.assertEquals(2, userMongoRepository.findAll().size());

        // 删除一个User，再验证User总数
        user = userMongoRepository.findByName("mama");
        userMongoRepository.delete(user);
        Assertions.assertEquals(1, userMongoRepository.findAll().size());
    }
}
