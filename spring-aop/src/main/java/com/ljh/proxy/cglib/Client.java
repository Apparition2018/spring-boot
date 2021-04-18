package com.ljh.proxy.cglib;

import com.ljh.proxy._static.RealSubject;
import com.ljh.proxy._static.Subject;
import org.springframework.cglib.proxy.Enhancer;

/**
 * Client
 *
 * @author Arsenal
 * created on 2020/1/2 16:54
 */
public class Client {

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(RealSubject.class);
        enhancer.setCallback(new DemoMethodInterceptor());
        Subject subject = (Subject) enhancer.create();
        subject.hello();
    }
}
