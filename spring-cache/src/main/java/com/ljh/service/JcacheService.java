package com.ljh.service;

import com.ljh.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ljh
 * created on 2021/7/13 17:12
 */
@Slf4j
@Service
// jcache 对应 ehcache.xml 中的 <cache></cache>
@CacheConfig(cacheNames = "jcache")
public class JcacheService {

    @Resource(name = "users")
    private List<User> users;

    @Cacheable(key = "#id")
    public User get(Integer id) {
        log.info("into get");
        return users.get(id);
    }
}
