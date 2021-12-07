package com.liangzc.mall.orderservice;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @PostMapping("/orders")
    public String getOrders(@RequestParam String goods,@RequestParam String marketings){

        return "订单创建成功!!!";
    }
}
