package com.ljh.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * UserRegistrationRequest
 *
 * @author ljh
 * created on 2022/6/9 1:42
 */
@Getter
@Setter
@NoArgsConstructor
public class UserRegistrationRequest {
    private String userId;
    private String approverId;
}
