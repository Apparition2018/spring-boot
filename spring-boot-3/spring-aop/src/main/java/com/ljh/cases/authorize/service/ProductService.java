package com.ljh.cases.authorize.service;

import com.ljh.cases.authorize.anno.AdminOnly;
import com.ljh.cases.authorize.domain.Product;
import org.springframework.stereotype.Service;

/**
 * ProduceService
 *
 * @author Arsenal
 * created on 2020/1/2 11:39
 */
@Service
public class ProductService {

    @AdminOnly
    public void insert(Product product) {
        System.out.println("insert product");
    }

    @AdminOnly
    public void delete(Long id) {
        System.out.println("delete product");
    }
}
