package com.woniu.woniuticket.cinema.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@FeignClient(name = "order")
public interface OrderClient {

    @RequestMapping("/queryAllOrders")
    public Map getAllOrder();
}

