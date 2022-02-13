package com.ljh.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * StockController
 *
 * @author ljh
 * created on 2022/2/14 0:36
 */
@RestController
@RequestMapping("stock")
public class StockController {
    
    @GetMapping("/test")
    public String test(String info) {
        return "Stock 模块接收到的信息 " + info;
    }
}
