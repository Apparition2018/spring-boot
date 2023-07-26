package com.ljh.proxy.chain;

import lombok.Getter;
import lombok.Setter;

/**
 * Handler
 *
 * @author Arsenal
 * created on 2020/1/2 17:24
 */
@Getter
@Setter
public abstract class Handler {

    private Handler success;

    public void execute() {
        handlerProcess();
        if (success != null) {
            success.execute();
        }
    }

    protected abstract void handlerProcess();
}
