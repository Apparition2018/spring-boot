package com.ljh.example.service;

import com.ljh.example.domain.Product;
import com.ljh.example.security.AdminOnly;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ProduceService
 *
 * @author Arsenal
 * created on 2020/1/2 11:39
 */
@Service
public class ProductService {
    
    private final AuthService authService;

    @Autowired
    public ProductService(AuthService authService) {
        this.authService = authService;
    }

    @AdminOnly
    public void insert(Product product) {
//        authService.checkAccess();
        System.out.println("insert product");
    }

    @AdminOnly
    public void delete(Long id) {
//        authService.checkAccess();
        System.out.println("delete product");
    }
    
}
