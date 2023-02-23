package com.ljh.service;

import com.ljh.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * CacheService
 *
 * @author ljh
 * @since 2023/2/23 8:49
 */
@Slf4j
@Service(CacheService.CACHE_NAME)
// 当使用 jcache 时，对应 ehcahce.xml 的 <cache alias/>
@CacheConfig(cacheNames = CacheService.CACHE_NAME)
public class CacheService {

    // ${spring.profiles.active}
    public static final String CACHE_NAME = "jcache";

    @Resource(name = "users")
    private List<User> users;

    @Cacheable(key = "#id")
    public User get(Integer id) {
        log.info("into get");
        return users.get(id);
    }
}
