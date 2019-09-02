package com.woniu.woniuticket.cinema.client;

import com.github.pagehelper.PageInfo;
import com.woniu.woniuticket.cinema.pojo.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.xml.crypto.Data;

@FeignClient(name = "user")
public interface UserClient {
    @RequestMapping("/queryAllOrders")
    public PageInfo<Order> getAllOrder(Data data);

}
