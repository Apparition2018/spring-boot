package com.ljh.service;

import com.ljh.entity.primary.User;
import com.ljh.repository.primary.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * UserService
 *
 * @author ljh
 * created on 2021/8/31 17:44
 */
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void saveThenThrow() {
        userRepository.save(new User("ljh", 31));
        throw new RuntimeException();
    }
}
