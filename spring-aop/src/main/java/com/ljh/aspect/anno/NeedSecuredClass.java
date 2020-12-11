package com.ljh.aspect.anno;

import java.lang.annotation.*;

/**
 * NeedSecuredClass
 *
 * @author Arsenal
 * created on 2020/1/2 15:22
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.TYPE)
@Inherited
public @interface NeedSecuredClass {
}
