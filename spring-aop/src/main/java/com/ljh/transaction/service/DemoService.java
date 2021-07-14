package com.ljh.transaction.service;

import com.ljh.transaction.dao.OperationLogDao;
import com.ljh.transaction.dao.UserDao;
import com.ljh.transaction.domain.OperationLog;
import com.ljh.transaction.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

/**
 * DemoService
 *
 * @author Arsenal
 * created on 2020/1/2 17:54
 */
@Component
public class DemoService {

    private final UserDao userDao;
    
    private final OperationLogDao operationLogDao;

    @Autowired
    public DemoService(UserDao userDao, OperationLogDao operationLogDao) {
        this.userDao = userDao;
        this.operationLogDao = operationLogDao;
    }
    
    @Transactional
    public void addUser(String name) {
        OperationLog log = new OperationLog();
        log.setContent("create user: " + name);
        operationLogDao.save(log);
        
        User user = new User();
        user.setName(name);
        userDao.save(user);
    }
}
