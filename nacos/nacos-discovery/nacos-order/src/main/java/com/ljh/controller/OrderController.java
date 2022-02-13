package com.ljh.controller;

import com.ljh.feign.StockFeignService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * OrderController
 *
 * @author ljh
 * created on 2022/2/14 0:46
 */
@RestController
@RequestMapping("order")
public class OrderController {

    private final StockFeignService stockFeignService;

    public OrderController(StockFeignService stockFeignService) {
        this.stockFeignService = stockFeignService;
    }

    @GetMapping("/test")
    public String test() {
        return stockFeignService.test("stock info");
    }
}
