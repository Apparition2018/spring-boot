package com.ljh.aspect.anno;

import java.lang.annotation.*;

/**
 * NeedSecured
 *
 * @author Arsenal
 * created on 2020/1/2 14:58
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
public @interface NeedSecured {
}
