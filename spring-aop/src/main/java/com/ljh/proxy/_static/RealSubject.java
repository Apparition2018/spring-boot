package com.ljh.proxy._static;

/**
 * RealSubject
 * 目标对象
 *
 * @author Arsenal
 * created on 2020/1/2 16:30
 */
public class RealSubject implements Subject {

    @Override
    public void request() {
        System.out.println("real subject execute request");
    }

    @Override
    public void hello() {
        System.out.println("hello");
    }
}
