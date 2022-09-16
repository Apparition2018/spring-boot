package com.ljh.aspect.config;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * ExecutionAspectConfig
 * <p>
 * 匹配任何公共方法
 * `@Pointcut("execution(public * com.ljh.aspect.service.*.*(..))")
 * <p>
 * 匹配 com.ljh 包及子包下 Service 类中无参方法
 * `@Pointcut("execution(* com.ljh..*Service.*())")
 * <p>
 * 匹配 com.ljh 包及子包下 Service 类中的任何只有一个参数的方法
 * `@Pointcut("execution(* com.ljh..*Service.*(*))")
 * <p>
 * 匹配 com.ljh 包及子包下任何类的任何方法
 * `@Pointcut("execution(* com.ljh..*.*(..))")
 * <p>
 * 匹配 com.ljh 包及子包下返回值为 String 的任何方法
 * `@Pointcut("execution(String com.ljh..*.*(..))")
 * <p>
 * 匹配异常
 * execution(public * com.ljh.aspect.service.*.*(..) throws java.lang.IllegalAccessException)
 *
 * @author Arsenal
 * created on 2020/1/2 14:25
 */
@Aspect
@Component
public class ExecutionAspectConfig {

//    @Pointcut("execution(public * com.ljh.aspect.service.*Service.*(..))")
//    public void matchCondition() {
//    }
//
//    @Before("matchCondition()")
//    public void before() {
//        System.out.println("");
//        System.out.println("###before");
//    }
}
