package com.ljh.example.service;

import com.ljh.example.security.CurrentUserHolder;
import org.springframework.stereotype.Component;

/**
 * AuthService
 *
 * @author Arsenal
 * created on 2020/1/2 11:43
 */
@Component
public class AuthService {
    
    public void checkAccess() {
        String user = CurrentUserHolder.get();
        if (!"admin".equals(user)) {
            throw new RuntimeException("operation not allow");
        }
    }
}
