package com.ljh.cases.datalog.enums;

import lombok.Getter;

/**
 * ActionType
 *
 * @author Arsenal
 * created on 2020/1/3 0:16
 */
@Getter
public enum ActionType {

    INSERT("添加", 1),

    UPDATE("更新", 2),

    DELETE("删除", 3);

    private final String name;

    private final int index;

    ActionType(String name, int index) {
        this.name = name;
        this.index = index;
    }
    
    public static String getName(int index) {
        for (ActionType actionType : ActionType.values()) {
            if (actionType.getIndex() == index) {
                return actionType.name;
            }
        }
        return null;
    }

    public static Integer getIndex(String name) {
        for (ActionType actionType : ActionType.values()) {
            if (actionType.getName().equals(name)) {
                return actionType.index;
            }
        }
        return null;
    }

    public static ActionType getActionType(int index) {
        for (ActionType actionType : ActionType.values()) {
            if (actionType.getIndex() == index) {
                return actionType;
            }
        }
        return null;
    }

}
