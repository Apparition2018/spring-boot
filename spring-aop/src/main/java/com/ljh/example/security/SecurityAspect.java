package com.ljh.example.security;

import com.ljh.example.service.AuthService;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * SecurityAspect
 *
 * @author Arsenal
 * created on 2020/1/2 13:02
 */
@Aspect
@Component
public class SecurityAspect {

    private final AuthService authService;

    @Autowired
    public SecurityAspect(AuthService authService) {
        this.authService = authService;
    }

    @Pointcut("@annotation(com.ljh.example.security.AdminOnly)")
    public void adminOnly() {

    }

    @Before("adminOnly()")
    public void check() {
        authService.checkAccess();
    }
}
