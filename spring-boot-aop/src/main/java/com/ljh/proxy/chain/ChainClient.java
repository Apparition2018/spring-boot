package com.ljh.proxy.chain;

import java.util.Arrays;
import java.util.List;

/**
 * ChainClient
 * 责任链模式
 *
 * @author Arsenal
 * created on 2020/1/2 17:34
 */
public class ChainClient {

    static class HandlerA extends ChainHandler {
        @Override
        protected void handlerProcess() {
            System.out.println("handle by a");
        }
    }

    static class HandlerB extends ChainHandler {
        @Override
        protected void handlerProcess() {
            System.out.println("handle by b");
        }
    }

    static class HandlerC extends ChainHandler {
        @Override
        protected void handlerProcess() {
            System.out.println("handle by c");
        }
    }

    public static void main(String[] args) {
        List<ChainHandler> handlers = Arrays.asList(
                new HandlerA(),
                new HandlerB(),
                new HandlerC()
        );
        Chain chain = new Chain(handlers);
        chain.proceed();
    }
}
