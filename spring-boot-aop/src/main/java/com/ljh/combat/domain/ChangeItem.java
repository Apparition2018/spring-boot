package com.ljh.combat.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * ChangeItem
 *
 * @author Arsenal
 * created on 2020/1/3 0:17
 */
@Getter
@Setter
public class ChangeItem {

    private String field;

    private String fieldShowName;

    private String oldValue;

    private String newValue;
}
