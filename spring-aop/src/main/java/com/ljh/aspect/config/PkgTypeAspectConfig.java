package com.ljh.aspect.config;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * PkgTypeAspectConfig
 *
 * 匹配 ProductService 类里头的所有方法
 * @Pointcut("within(com.ljh.service.ProductService)")
 * 
 * 匹配 com.ljh 包及子包下所有方法
 * @Pointcut("within(com.ljh..*)")
 *
 * @author Arsenal
 * created on 2020/1/2 14:16
 */
@Aspect
@Component
public class PkgTypeAspectConfig {

//    @Pointcut("within(com.ljh.aspect.service.ProductService)")
//    public void matchType() {
//    }
//    
//    @Before("matchType()")
//    public void before() {
//        System.out.println("");
//        System.out.println("###before");
//    }
}
