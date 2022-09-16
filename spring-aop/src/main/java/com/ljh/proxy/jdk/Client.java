package com.ljh.proxy.jdk;

import com.ljh.proxy._static.RealSubject;
import com.ljh.proxy._static.Subject;

import java.lang.reflect.Proxy;

/**
 * Client
 *
 * @author Arsenal
 * created on 2020/1/2 16:40
 */
public class Client {

    public static void main(String[] args) {
        // 生成JDK动态代理代理类文件：https://www.freesion.com/article/4664896920/
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

        Subject subject = (Subject) Proxy.newProxyInstance(Client.class.getClassLoader(), new Class[]{Subject.class}, new JdkProxy(new RealSubject()));
        subject.hello();
    }
}
