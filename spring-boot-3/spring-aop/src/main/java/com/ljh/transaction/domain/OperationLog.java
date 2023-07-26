package com.ljh.transaction.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * OperationLog
 *
 * @author Arsenal
 * created on 2020/1/2 17:51
 */
@Entity
@Getter
@Setter
public class OperationLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private Date createAt;

    @PrePersist
    public void settingTime() {
        createAt = new Date();
    }
}
