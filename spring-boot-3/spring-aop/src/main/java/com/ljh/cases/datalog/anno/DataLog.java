package com.ljh.cases.datalog.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Datalog
 *
 * @author Arsenal
 * created on 2020/1/3 0:21
 */
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface DataLog {
    // 中文字段名
    String name();
}
