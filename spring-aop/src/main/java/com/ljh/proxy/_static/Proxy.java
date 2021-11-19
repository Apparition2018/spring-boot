package com.ljh.proxy._static;

/**
 * Proxy
 * 代理对象
 *
 * @author Arsenal
 * created on 2020/1/2 16:31
 */
public class Proxy implements Subject {
    
    private final RealSubject realSubject;
    
    public Proxy(RealSubject realSubject) {
        this.realSubject = realSubject;
    }

    @Override
    public void request() {
        System.out.println("before");
        try {
            realSubject.request();
        } catch (Exception e) {
            System.out.println("ex: " + e.getMessage());
            throw e;
        } finally {
            System.out.println("after");
        }
    }

    @Override
    public void hello() {
        realSubject.hello();
    }
}
