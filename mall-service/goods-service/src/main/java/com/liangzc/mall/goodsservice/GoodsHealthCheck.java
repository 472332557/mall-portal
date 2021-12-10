package com.liangzc.mall.goodsservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class GoodsHealthCheck {

    @GetMapping("/healthCheck")
    public String check(){

        log.info("health check....");
        return "SUCCESS";
    }

}
