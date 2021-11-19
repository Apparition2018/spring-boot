package com.ljh.proxy.chain;

import java.util.List;

/**
 * Chain
 *
 * @author Arsenal
 * created on 2020/1/2 17:30
 */
public class Chain {
    
    private final List<ChainHandler> handlers;
    
    private int index = 0;

    public Chain(List<ChainHandler> handlers) {
        this.handlers = handlers;
    }
    
    public void proceed() {
        if (index >= handlers.size()) {
            return;
        }
        handlers.get(index++).execute(this);
    }
}
