package com.ljh;

import com.ljh.example.security.CurrentUserHolder;
import com.ljh.example.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class ExampleTests {
    
    private final ProductService productService;

    @Autowired
    ExampleTests(ProductService productService) {
        this.productService = productService;
    }

    @Test
    public void anonInsertTest() {
        CurrentUserHolder.set("tom");
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            productService.delete(1L);
        });
        assertEquals("operation not allow", exception.getMessage());
    }
    
    @Test
    public void adminInsertTest() {
        CurrentUserHolder.set("admin");
        productService.delete(1L);
    }
}
