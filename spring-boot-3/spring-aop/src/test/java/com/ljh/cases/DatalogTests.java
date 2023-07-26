package com.ljh.cases;

import com.ljh.cases.datalog.dao.ActionDao;
import com.ljh.cases.datalog.dao.ProductDao;
import com.ljh.cases.datalog.domain.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

/**
 * CaseTests
 *
 * @author Arsenal
 * created on 2020/1/3 1:56
 */
@SpringBootTest
public class DatalogTests {

    @Autowired
    private ProductDao productDao;
    @Autowired
    private ActionDao actionDao;

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

    @Test
    public void testFindAllAction() {
        System.err.println(actionDao.findAll());
    }
}
