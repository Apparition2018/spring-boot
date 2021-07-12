package com.ljh.service;

import com.ljh.component.InitData;
import com.ljh.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * UserService
 *
 * @author ljh
 * created on 2021/7/12 17:52
 */
@Slf4j
@Service
@CacheConfig(cacheNames = "user")
public class UserService {

    private final InitData initData;

    @Autowired
    public UserService(InitData initData) {
        this.initData = initData;
    }

    // 因为类配置了 @CacheConfig(cacheNames = "user")，所以可以省略 cacheNames = "user",
    @Cacheable(/*cacheNames = "user", */key = "#id")
    public User get(Integer id) {
        log.info("into get");
        return initData.getUsers().get(id);
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
        return initData.getUsers().get(id);
    }

    @Cacheable(key = "#name")
    public User getByName(String name) {
        log.info("into getByName");
        return null;
    }

    @Cacheable(cacheNames = "userList")
    public List<User> list() {
        log.info("into list");
        return initData.getUsers();
    }

    @CachePut(key = "#result.id")
    public User update(User user) {
        log.info("into update");
        User oldUser = initData.getUsers().get(user.getId());
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
}
