package com.ljh.cases.datalog.domain;

import com.ljh.cases.datalog.anno.DataLog;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Product
 *
 * @author Arsenal
 * created on 2020/1/3 0:18
 */
@Entity
@Getter
@Setter
@ToString
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @DataLog(name = "产品名称")
    private String name;
    private String category;
    private String detail;
    private BigDecimal buyPrice;
    private BigDecimal sellPrice;
    private String provider;
    private Date onlineTime;
    private Date updateTime;
}
