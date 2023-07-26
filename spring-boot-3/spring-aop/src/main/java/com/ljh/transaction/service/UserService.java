package com.ljh.transaction.service;

import com.ljh.transaction.dao.OperationLogDao;
import com.ljh.transaction.dao.UserDao;
import com.ljh.transaction.domain.OperationLog;
import com.ljh.transaction.domain.User;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;


/**
 * UserService
 *
 * @author Arsenal
 * created on 2020/1/2 17:54
 */
@Component
@AllArgsConstructor
public class UserService {

    private final UserDao userDao;
    private final OperationLogDao operationLogDao;

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
