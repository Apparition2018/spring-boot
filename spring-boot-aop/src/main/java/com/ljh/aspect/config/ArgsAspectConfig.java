package com.ljh.aspect.config;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * ArgsAspectConfig
 *
 * 匹配任何以 find 开头而且只有一个 Long 参数的方法
 * @Pointcut("execution(* *..find*(Long))")
 * 
 * 匹配任何以 find 开头的而且第一个参数为 Long 型的方法
 * @Pointcut("execution(* *..find*(Long,..))")
 * 
 * 匹配任何只有一个 Long 参数的方法
 * @Pointcut("within(com.ljh..*) && args(Long)")
 * 
 * 匹配第一个参数为 Long 型的方法
 * @Pointcut("within(com.ljh..*) && args(Long,..)")
 * 
 * @author Arsenal
 * created on 2020/1/2 15:16
 */
@Aspect
@Component
public class ArgsAspectConfig {
    
//    @Pointcut("args(Long, ..) && within(com.ljh.aspect.service.*)")
//    public void matchArgs() {
//        
//    }
//    
//    @Before("matchArgs()")
//    public void before() {
//        System.out.println("");
//        System.out.println("###before");
//    }
}
