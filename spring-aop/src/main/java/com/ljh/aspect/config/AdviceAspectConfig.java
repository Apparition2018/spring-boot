package com.ljh.aspect.config;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * AdviceAspectConfig
 *
 * @author Arsenal
 * created on 2020/1/2 15:44
 */
@Aspect
@Component
public class AdviceAspectConfig {

    @Pointcut("@annotation(com.ljh.aspect.anno.AdminOnly) && within(com.ljh..*)")
    public void matchAnn() {
    }

    @Pointcut("execution(* *..find*(Long)) && within(com.ljh..*) ")
    public void matchArg() {
    }

    @Pointcut("execution(public * com.ljh.aspect.service..*Service.*(..) throws java.lang.IllegalAccessException) && within(com.ljh..*)")
    public void matchException() {
    }

    @Pointcut("execution(String com.ljh..*.*(..)) && within(com.ljh..*)")
    public void matchReturn() {
    }

    @Before("matchArg() && args(productId)")
    public void before(Long productId) {
        System.out.println("### before, get args:" + productId);
    }

//    @Around(value = "matchAnn()")
//    @Around(value = "matchException()")
//    public Object around(ProceedingJoinPoint joinPoint) {
//        System.out.println("###around");
//        Object result = null;
//        try {
//            result = joinPoint.proceed(joinPoint.getArgs());
//            System.out.println("###after returning");
//        } catch (Throwable throwable) {
//            System.out.println("###after throwing");
//            throwable.printStackTrace();
//        } finally {
//            System.out.println("###finally");
//        }
//        return result;
//    }
}
