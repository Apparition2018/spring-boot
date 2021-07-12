package com.ljh.service;

import com.ljh.component.InitData;
import com.ljh.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * UserService
 *
 * @author ljh
 * created on 2021/7/12 17:52
 */
@Slf4j
@Service
public class UserService {

    private final InitData initData;

    @Autowired
    public UserService(InitData initData) {
        this.initData = initData;
    }

    @Cacheable(cacheNames = "user", key = "#p0")
    public User get(Integer id) {
        log.info("into get");
        return initData.getUsers().get(id);
    }

    @CachePut(cacheNames = "user", key = "#p0")
    public User update(User user) {
        log.info("into update");
        return initData.getUsers().get(id);
    }

}
