package com.ljh.aspect.anno;

import java.lang.annotation.*;

/**
 * NeedSecuredSource
 *
 * @author Arsenal
 * created on 2020/1/2 15:23
 */
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
@Inherited
public @interface NeedSecuredSource {
}
