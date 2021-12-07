package com.liangzc.mall.goodsservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class GoodsController {


    @Value("${server.port}")
    private String port;

    @GetMapping("/goods")
    public String getGoods(){

        log.info("商品端口为：{}", port);
        return "SUCCESS";
    }

}
