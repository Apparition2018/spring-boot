package com.ljh.aspect.service;

import com.ljh.aspect.anno.NeedSecured;
import com.ljh.aspect.anno.NeedSecuredClass;
import com.ljh.aspect.anno.NeedSecuredSource;
import org.springframework.stereotype.Component;

/**
 * ProductService
 *
 * @author Arsenal
 * created on 2020/1/2 14:28
 */
@Component("productService2")
@NeedSecured
@NeedSecuredClass
@NeedSecuredSource
public class ProductService {

    public String getName() {
        System.out.println("execute get name");
        return "product service";
    }

    public void exDemo() throws IllegalAccessException {
        System.out.println("execute ex demo");
        throw new IllegalAccessException("TEST");
    }

    public void findById(Long id) {
        System.out.println("execute find by id");
    }

    public void findByTwoArgs(Long id, String name) {
        System.out.println("execute find by id and Name");
    }
}
