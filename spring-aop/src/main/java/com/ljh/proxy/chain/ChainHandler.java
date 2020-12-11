package com.ljh.proxy.chain;

/**
 * ChainHandler
 *
 * @author Arsenal
 * created on 2020/1/2 17:30
 */
public abstract class ChainHandler {
    
    public void execute(Chain chain) {
        handlerProcess();
        chain.proceed();
    }

    protected abstract void handlerProcess();
}
