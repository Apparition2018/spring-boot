package com.ljh.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * Quote
 *
 * @author ljh
 * created on 2021/7/15 0:03
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Quote {

    private Long id;
    private String quote;



}
