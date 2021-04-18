package com.ljh;

import com.ljh.combat.dao.ProductDao;
import com.ljh.combat.domain.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

/**
 * CombatTests
 *
 * @author Arsenal
 * created on 2020/1/3 1:56
 */
@SpringBootTest
public class CombatTests {

    private final ProductDao productDao;

    @Autowired
    public CombatTests(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Test
    public void testInsert() {
        Product product = new Product();
        product.setName("dell computer");
        product.setOnlineTime(new Date());
        product.setBuyPrice(new BigDecimal("29.5"));
        product.setCategory("computer");
        product.setDetail("this is a dell notebook");
        product.setUpdateTime(new Date());
        productDao.save(product);
        System.out.println("new product id:" + product.getId());
    }

    @Test
    public void testUpdate() {
        Optional<Product> productById = productDao.findById(1L);
        Product product = productById.orElse(new Product());
        product.setName("test-update");
        product.setBuyPrice(new BigDecimal("23.5"));
        product.setOnlineTime(new Date());
        productDao.save(product);
    }

    @Test
    public void testDelete() {
        productDao.deleteById(1L);
    }
}
