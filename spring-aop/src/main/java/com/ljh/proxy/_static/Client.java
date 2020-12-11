package com.ljh.proxy._static;

/**
 * Client
 * 静态代理模式
 *
 * @author Arsenal
 * created on 2020/1/2 16:33
 */
public class Client {

    public static void main(String[] args) {
        Subject subject = new Proxy(new RealSubject());
        subject.request();
    }
}
