package com.ljh.proxy.cglib;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * DemoMethodInterceptor
 *
 * @author Arsenal
 * created on 2020/1/2 16:52
 */
public class DemoMethodInterceptor implements MethodInterceptor {

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("before");
        Object result;
        try {
            result = methodProxy.invokeSuper(o, objects);
        } catch (Exception e) {
            System.out.println("ex: " + e.getMessage());
            throw e;
        } finally {
            System.out.println("after");
        }
        return result;
    }
}
