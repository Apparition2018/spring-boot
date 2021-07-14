package com.ljh.annotation;

import org.springframework.cache.annotation.Cacheable;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * UserCacheable
 *
 * @author ljh
 * created on 2021/7/14 12:16
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Cacheable(cacheNames="user", key="#p0")
public @interface UserCacheable {
}
