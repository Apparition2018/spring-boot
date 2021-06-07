package com.ljh.case_.domain;

import com.ljh.case_.datalog.DataLog;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Product
 *
 * @author Arsenal
 * created on 2020/1/3 0:18
 */
@Entity
@Data
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
