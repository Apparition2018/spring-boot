package com.ljh.proxy.chain;

/**
 * Client
 * 责任链模式
 *
 * @author Arsenal
 * created on 2020/1/2 17:26
 */
public class Client {
    
    static class HandlerA extends Handler {
        @Override
        protected void handlerProcess() {
            System.out.println("handle by a");
        }
    }
    
    static class HandlerB extends Handler {
        @Override
        protected void handlerProcess() {
            System.out.println("handle by b");
        }
    }

    static class HandlerC extends Handler {
        @Override
        protected void handlerProcess() {
            System.out.println("handle by c");
        }
    }

    public static void main(String[] args) {
        Handler handlerA = new HandlerA();
        Handler handlerB = new HandlerB();
        Handler handlerC = new HandlerC();
        
        handlerA.setSuccess(handlerB);
        handlerB.setSuccess(handlerC);
        
        handlerA.execute();
    }
}
