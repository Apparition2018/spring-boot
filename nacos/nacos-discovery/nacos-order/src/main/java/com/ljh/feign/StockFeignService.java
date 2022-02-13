package com.ljh.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * StockFeignService
 *
 * @author ljh
 * created on 2022/2/14 0:41
 */
@FeignClient("nacos-stock")
public interface StockFeignService {

    @GetMapping("/stock/test")
    String test(@RequestParam("info") String info);
}
