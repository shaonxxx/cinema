package com.woniu.woniuticket.cinema.client;


import com.github.pagehelper.PageInfo;
import com.woniu.woniuticket.cinema.pojo.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "order")
public interface OrderClient {

    @RequestMapping("/queryAllOrders")
    public PageInfo<Order> getAllOrder(@RequestParam(value = "currentPage",defaultValue = "1",required = false) Integer currentPage,
                                       @RequestParam(value = "pageSize",defaultValue = "10",required = false) Integer pageSize,
                                       @RequestParam(value = "startDay",required = false) String startDay,
                                       @RequestParam(value = "endDay",required = false) String endDay,
                                       @RequestParam(value = "payType",required = false) String payType,
                                       @RequestParam(value = "orderState",required = false) String orderState,
                                       @RequestParam(value = "orderNum",required = false) String orderNum);
    @Component
    static class OrderClienImpl implements OrderClient{

        @Override
        public PageInfo<Order> getAllOrder(Integer currentPage, Integer pageSize, String startDay, String endDay, String payType, String orderState, String orderNum) {

            return new PageInfo<Order>();
        }
    }
}

