package com.ljh.config;

import com.ljh.entity.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * 初始化数据
 *
 * @author ljh
 * created on 2021/7/12 17:46
 */
@Configuration
public class InitDataRunner implements CommandLineRunner {
    private final List<User> users = new ArrayList<>();

    @Override
    public void run(String... args) throws Exception {
        for (int i = 0; i < 10; i++) {
            User user = new User(i, "姓名" + i, i + "@163.com", i % 2);
            users.add(user);
        }
    }

    @Bean("users")
    public List<User> getUsers() {
        return users;
    }
}
