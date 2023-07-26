package com.ljh.cases;

import com.ljh.cases.authorize.holder.CurrentUserHolder;
import com.ljh.cases.authorize.service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AuthorizeTests {

    @Autowired
    private ProductService productService;

    @Test
    public void insertTest() {
        CurrentUserHolder.set("tom");
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> productService.delete(1L));
        Assertions.assertEquals("operation not allow", exception.getMessage());
    }

    @Test
    public void deleteTest() {
        CurrentUserHolder.set("admin");
        productService.delete(1L);
    }
}
