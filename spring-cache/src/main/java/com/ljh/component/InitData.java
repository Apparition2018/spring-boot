package com.ljh.component;

import com.ljh.entity.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 初始化数据
 *
 * @author ljh
 * created on 2021/7/12 17:46
 */
@Component
public class InitData implements CommandLineRunner {
    private final List<User> users = new ArrayList<>();

    @Override
    public void run(String... args) throws Exception {
        for (int i = 0; i < 10; i++) {
            User user = new User(1, "姓名" + i, i % 2);
            users.add(user);
        }
    }

    public List<User> getUsers() {
        return users;
    }
}
