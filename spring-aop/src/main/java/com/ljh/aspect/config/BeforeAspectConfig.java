package com.ljh.aspect.config;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * BeforeAspectConfig
 *
 * @author Arsenal
 * created on 2020/1/2 15:57
 */
@Aspect
@Component
public class BeforeAspectConfig {

    @Pointcut("execution(* *..find*(Long)) && within(com.ljh..*) ")
    public void matchArg() {
    }

    @Before("matchArg() && args(productId)")
    public void before(Long productId) {
        System.out.println("### before, get args:" + productId);
    }
}
