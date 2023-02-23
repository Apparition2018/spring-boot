package com.ljh.service;

import com.ljh.annotation.UserCacheable;
import com.ljh.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * SimpleService
 *
 * @author ljh
 * created on 2021/7/12 17:52
 */
@Slf4j
@Service
@CacheConfig(cacheNames = "simple")
public class SimpleService {

    @Resource(name = "users")
    private List<User> users;

    // 因为类配置了 @CacheConfig(cacheNames = "simple")，所以可以省略 cacheNames = "simple",
    @Cacheable(/*cacheNames = "simple", */key = "#id")
    public User get(Integer id) {
        log.info("into get");
        return users.get(id);
    }

    @CachePut(key = "#result.id")
    public User update(User user) {
        log.info("into update");
        User oldUser = users.get(user.getId());
        BeanUtils.copyProperties(user, oldUser, "id");
        return oldUser;
    }

    @CacheEvict(key = "#p0")
    public void delete(Integer id) {
        log.info("into delete");
    }

    @CacheEvict(allEntries = true, beforeInvocation = true)
    public void deleteAll() {
        log.info("into deleteAll");
    }

    /**
     * 除了以 id 为 key 缓存对象，还以 name 为 key 和 email 为 key 缓存对象
     */
    @Caching(
            cacheable = {
                    @Cacheable(key = "#id")
            },
            put = {
                    @CachePut(key = "#result.name"), @CachePut(key = "#result.email")
            }
    )
    public User get2(Integer id) {
        log.info("into get2");
        return users.get(id);
    }

    @Cacheable(key = "#name")
    public User getByName(String name) {
        log.info("into getByName");
        return null;
    }

    @Cacheable(keyGenerator = "myKeyGenerator")
    public List<User> list() {
        log.info("into list");
        return users;
    }

    @UserCacheable
    public User get3(Integer id) {
        log.info("into get3");
        return users.get(id);
    }
}
