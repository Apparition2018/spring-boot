package com.ljh.proxy.jdk;

import com.ljh.proxy._static.RealSubject;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * JdkProxy
 * 代理对象
 *
 * @author Arsenal
 * created on 2020/1/2 16:38
 */
public class JdkProxy implements InvocationHandler {

    private final RealSubject realSubject;

    public JdkProxy(RealSubject realSubject) {
        this.realSubject = realSubject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before");
        Object result;
        try {
            result = method.invoke(realSubject, args);
        } catch (Exception e) {
            System.out.println("ex: " + e.getMessage());
            throw e;
        } finally {
            System.out.println("after");
        }
        return result;
    }
}
