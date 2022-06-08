package com.ljh.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * TaskCompletionRequest
 *
 * @author ljh
 * created on 2022/6/9 1:43
 */
@Getter
@Setter
@NoArgsConstructor
public class TaskCompletionRequest {
    private boolean approved;
    private String comment;
}
