package com.ljh.kafka.common;

import lombok.Getter;
import lombok.Setter;

/**
 * Response
 *
 * @author Arsenal
 * created on 2021/3/14 0:53
 */
@Getter
@Setter
public class Response {
    private int code;
    private String message;

    public Response(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
