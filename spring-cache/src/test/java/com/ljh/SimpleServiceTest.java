package com.ljh;

import com.ljh.entity.User;
import com.ljh.service.SimpleService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;

import java.util.Random;

/**
 * @author ljh
 * created on 2021/7/12 17:55
 */
@Slf4j
@SpringBootTest
public class SimpleServiceTest {

    private final SimpleService simpleService;
    private final CacheManager cacheManager;

    @Autowired
    public SimpleServiceTest(SimpleService simpleService, CacheManager cacheManager) {
        this.simpleService = simpleService;
        this.cacheManager = cacheManager;
    }

    @Test
    public void cacheable() {
        System.out.println("CacheManager Type: " + cacheManager.getClass());
        log.info(simpleService.get(1).toString());
        // 第一次调用还没有缓存，所以执行了方法，输出 into get
        // into get
        // User(id=1, name=姓名1, email=1@163.com, gender=1)
        log.info(simpleService.get(1).toString());
        // 第二次调用有缓存，所以没有执行方法，直接返回缓存数据
        // User(id=1, name=姓名1, email=1@163.com, gender=1)
    }

    @Test
    public void cachePut() {
        User user = new User(1, "姓名11", "11@163.com", 1);
        log.info(simpleService.update(user).toString());
        // into update
        // User(id=1, name=姓名11, email=11@163.com, gender=1)
        log.info(simpleService.get(user.getId()).toString());
        // @CachePut 注解的 update() 缓存了返回值，所以没有执行方法，直接返回缓存数据
        // User(id=1, name=姓名11, email=11@163.com, gender=1)
    }

    @Test
    public void cacheEvict() {
        simpleService.get(1);
        simpleService.delete(1);
        // into delete
        simpleService.get(1);
        // @CacheEvict 注解的 delete() 删除了缓存，所以执行了方法，输出 into get
        // into get
    }

    @Test
    public void cacheEvictAllEntries() {
        for (int i = 0; i < 10; i++) {
            simpleService.get(i);
        }
        simpleService.deleteAll();
        simpleService.get(new Random().nextInt(10));
        // @CacheEvict 注解的 deleteAll() 删除了缓所有存，所以无论获取那个 id 的对象都执行了方法，输出 into get
        // into get
    }

    @Test
    public void caching() {
        log.info(simpleService.get2(1).toString());
        log.info(simpleService.getByName("姓名1").toString());
        // @Caching 注解的 get2() 缓存了以 name 为 key 的对象，所以没有执行方法，直接返回缓存数据
        // User(id=1, name=姓名1, email=1@163.com, gender=1)
    }

    @Test
    public void myKeyGenerator() {
        log.info(simpleService.list().toString());
    }

    @Test
    public void myAnnotation() {
        log.info(simpleService.get3(1).toString());
        log.info(simpleService.get3(1).toString());
    }
}
