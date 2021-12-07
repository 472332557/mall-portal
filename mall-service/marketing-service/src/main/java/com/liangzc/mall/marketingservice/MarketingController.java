package com.liangzc.mall.marketingservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MarketingController {

    @GetMapping("/marketing")
    public String getMarketing(){
        return "SUCCESS";
    }
}
