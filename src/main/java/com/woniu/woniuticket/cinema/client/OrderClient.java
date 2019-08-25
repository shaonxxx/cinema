package com.woniu.woniuticket.cinema.client;


import com.github.pagehelper.PageInfo;
import com.woniu.woniuticket.cinema.pojo.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "order")
public interface OrderClient {

    @RequestMapping("/queryAllOrders")
    public PageInfo<Order> getAllOrder();
}

