package com.ljh;

import com.ljh.entity.User;
import com.ljh.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;

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

    /**
     * 演示 @Cacheable
     */
    @Test
    public void get() {
        log.info(userService.get(1).toString());
        // 第一次调用还没有缓存，所以执行了方法，输出 into get
        // into get
        // User(id=1, name=姓名1, email=1@163.com, gender=1)
        log.info(userService.get(1).toString());
        // 第二次调用有缓存，所以没有执行方法，直接返回缓存数据
        // User(id=1, name=姓名1, email=1@163.com, gender=1)
    }

    /**
     * 演示 @Caching
     */
    @Test
    public void get2() {
        log.info(userService.get2(1).toString());
        log.info(userService.getByName("姓名1").toString());
        // @Caching 注解的 get2() 缓存了以 name 为 key 的对象，所以没有执行方法，直接返回缓存数据
        // User(id=1, name=姓名1, email=1@163.com, gender=1)
    }

    /**
     * 演示 @CachePut
     */
    @Test
    public void update() {
        User user = new User(1, "姓名11", "11@163.com", 1);
        log.info(userService.update(user).toString());
        // into update
        // User(id=1, name=姓名11, email=11@163.com, gender=1)
        log.info(userService.get(user.getId()).toString());
        // @CachePut 注解的 update() 缓存了返回值，所以没有执行方法，直接返回缓存数据
        // User(id=1, name=姓名11, email=11@163.com, gender=1)
    }

    /**
     * 演示 @CacheEvict
     */
    @Test
    public void delete() {
        userService.get(1);
        userService.delete(1);
        // into delete
        userService.get(1);
        // @CacheEvict 注解的 delete() 删除了缓存，所以执行了方法，输出 into get
        // into get
    }

    /**
     * 演示 @CacheEvict(allEntries = true)
     */
    @Test
    public void deleteAll() {
        for (int i = 0; i < 10; i++) {
            userService.get(i);
        }
        userService.deleteAll();
        userService.get(new Random().nextInt(10));
        // @CacheEvict 注解的 deleteAll() 删除了缓所有存，所以无论获取那个 id 的对象都执行了方法，输出 into get
        // into get
    }
}
