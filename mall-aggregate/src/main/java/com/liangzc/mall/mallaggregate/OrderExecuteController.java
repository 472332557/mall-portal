package com.liangzc.mall.mallaggregate;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Locale;

@Slf4j
@RestController
public class OrderExecuteController {

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/executeOrder")
    public String orderexecuteOld(){

        //获取商品信息
        String goodsInfo = restTemplate.getForObject("http://localhost:9090/goods", String.class);
        log.info("获取商品信息：{}",goodsInfo);
        //获取营销信息
        String marketingInfo = restTemplate.getForObject("http://localhost:9091/marketing", String.class);
        log.info("获取营销信息：{}",goodsInfo);
        //获取订单信息
        MultiValueMap<String, Object> multiValueMap = new LinkedMultiValueMap<>();
        multiValueMap.add("goods", goodsInfo);
        multiValueMap.add("marketings", marketingInfo);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://localhost:9092/orders", multiValueMap, String.class);
        return responseEntity.getBody();
    }


    @GetMapping("/executeOrderByRibbon")
    public String orderexecuteByRibbon(){

        ServiceInstance instance = loadBalancerClient.choose("goods-service");
        String url = String.format("http://%s:%s/%s", instance.getHost(), instance.getPort(),"goods");
        log.info("url={}",url);
        //获取商品信息
        String goodsInfo = restTemplate.getForObject(url, String.class);
        log.info("获取商品信息：{}",goodsInfo);
        //获取营销信息
        String marketingInfo = restTemplate.getForObject("http://localhost:9091/marketing", String.class);
        log.info("获取营销信息：{}",goodsInfo);
        //获取订单信息
        MultiValueMap<String, Object> multiValueMap = new LinkedMultiValueMap<>();
        multiValueMap.add("goods", goodsInfo);
        multiValueMap.add("marketings", marketingInfo);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://localhost:9092/orders", multiValueMap, String.class);
        return responseEntity.getBody();
    }
}
