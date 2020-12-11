package com.ljh.aspect.config;

import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * AnnAspectConfig
 *
 * 匹配方法标注有 AdminOnly 的注解的方法
 * @Pointcut("@annotation(com.ljh.aspect.anno.AdminOnly) && within(com.ljh..*)")
 * 
 * 匹配标注有 NeedSecured 的类底下的方法，要求 RetentionPolicy 级别为 class 级别
 * @Pointcut("@within(com.ljh.aspect.anno.NeedSecured) && within(com.ljh..*)")
 * 
 * 匹配标注有 NeedSecured 的类及其子类的方法，要求 RetentionPolicy 级别为 runtime 级别
 * 在 spring context 的环境下，二者没有区别
 * @Pointcut("@target(com.ljh.aspect.anno.NeedSecured) && within(com.ljh..*)")
 * 
 * 匹配传入的参数类标注有 NeedSecured 注解的方法
 * @Pointcut("@args(com.ljh.aspect.anno.NeedSecured) && within(com.ljh..*)")
 * 
 * @author Arsenal
 * created on 2020/1/2 15:21
 */
public class AnnAspectConfig {
    
    @Pointcut("@annotation(com.ljh.aspect.anno.AdminOnly) && within(com.ljh..*)")
    public void matchAnn() {
        
    }

    @Before("matchAnn()")
    public void before() {
        System.out.println("");
        System.out.println("###before");
    }
}
