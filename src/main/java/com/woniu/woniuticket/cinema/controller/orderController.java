package com.woniu.woniuticket.cinema.controller;

import com.github.pagehelper.PageInfo;
import com.woniu.woniuticket.cinema.client.OrderClient;
import com.woniu.woniuticket.cinema.pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
public class orderController {
    @Autowired
    OrderClient orderClient;

    /**
     *  查询所有订单
     * @return 所有订单集合
     */
    @GetMapping("/order")
    public Map testOrder(@RequestParam(value = "currentPage",defaultValue = "1",required = false) Integer currentPage,
                           @RequestParam(value = "pageSize",defaultValue = "10",required = false) Integer pageSize,
                           @RequestParam(value = "startDay",required = false) String startDay,
                           @RequestParam(value = "endDay",required = false) String endDay,
                           @RequestParam(value = "payType",required = false) String payType,
                           @RequestParam(value = "orderState",required = false) String orderState,
                           @RequestParam(value = "orderNum",required = false) String orderNum){
        Map map = new HashMap<>();
        PageInfo<Order> pageInfo=orderClient.getAllOrder(currentPage,pageSize,startDay,endDay,payType,orderState,orderNum);
        map.put("pageInfo",pageInfo);

        return map;
    }


    @GetMapping("/Orderlist")
    public ModelAndView listOrder(@RequestParam(value = "currentPage",defaultValue = "1",required = false) Integer currentPage,
                                  @RequestParam(value = "pageSize",defaultValue = "10",required = false) Integer pageSize,
                                  @RequestParam(value = "startDay",required = false) String startDay,
                                  @RequestParam(value = "endDay",required = false) String endDay,
                                  @RequestParam(value = "payType",required = false) String payType,
                                  @RequestParam(value = "orderState",required = false) String orderState,
                                  @RequestParam(value = "orderNum",required = false) String orderNum){

        ModelAndView mv=new ModelAndView();
        PageInfo<Order> PageInfo =
                orderClient.getAllOrder(currentPage,pageSize,startDay,endDay,payType,orderState,orderNum);

        mv.addObject("pageInfo",PageInfo);
        mv.setViewName("order-list");
        return mv;
    }

    //@RequestMapping("/orders/show.do")


}
