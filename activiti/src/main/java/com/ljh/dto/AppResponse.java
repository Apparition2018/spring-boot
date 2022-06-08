package com.ljh.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * AppResponse
 *
 * @author ljh
 * created on 2022/6/9 1:44
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppResponse {
    private Integer statusCode;
    private HttpStatus status;
    private String message;
    private Object data;
}
