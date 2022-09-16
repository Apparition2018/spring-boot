package com.ljh.aspect.log;

import com.ljh.aspect.anno.AdminOnly;
import org.springframework.stereotype.Component;

/**
 * LogService
 *
 * @author Arsenal
 * created on 2020/1/2 14:56
 */
@Component
public class LogService implements Loggable {

    @Override
    @AdminOnly
    public void log() {
        System.out.println("log from LogService");
    }
}
