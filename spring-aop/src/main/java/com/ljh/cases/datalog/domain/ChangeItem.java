package com.ljh.cases.datalog.domain;

import lombok.Data;

/**
 * ChangeItem
 *
 * @author Arsenal
 * created on 2020/1/3 0:17
 */
@Data
public class ChangeItem {

    private String field;

    private String fieldShowName;

    private String oldValue;

    private String newValue;
}
