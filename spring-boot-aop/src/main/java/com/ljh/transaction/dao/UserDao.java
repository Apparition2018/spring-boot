package com.ljh.transaction.dao;

import com.ljh.transaction.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * UserDao
 *
 * @author Arsenal
 * created on 2020/1/2 17:52
 */
public interface UserDao extends JpaRepository<User, Long> {
}
