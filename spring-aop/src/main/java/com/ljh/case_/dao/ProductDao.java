package com.ljh.case_.dao;

import com.ljh.case_.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * ProductDao
 *
 * @author Arsenal
 * created on 2020/1/3 0:20
 */
public interface ProductDao extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE p.id = ?1")
    public Product findById2(Long id);
}
