package com.ljh.aspect.config;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * ObjectAspectConfig
 *
 * 匹配 AOP 对象的目标对象为指定类型的方法，即 LogService 的 AOP 代理对象的方法
 * @Pointcut("this(com.ljh.log.Loggable)")
 * 
 * 匹配实现 Loggable 接口的目标对象(而不是 AOP 代理后的对象)的方法
 * @Pointcut("target(com.ljh.log.Loggable)")
 * this 可以拦截 DeclareParents(Introduction)
 * target 不拦截 DeclareParents(Introduction)
 * 
 * 匹配所有以 Service 结尾的 Bean 里头的方法
 * @Pointcut("bean(*Service)")
 * 
 * @author Arsenal
 * created on 2020/1/2 14:50
 */
@Aspect
@Component
public class ObjectAspectConfig {
    
//    @Pointcut("target(com.ljh.aspect.log.Loggable)")
//    public void matchCondition() {
//        
//    }
//    
//    @Before("matchCondition()")
//    public void before() {
//        System.out.println("");
//        System.out.println("###before");
//    }
}
