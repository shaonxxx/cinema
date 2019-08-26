package com.woniu.woniuticket.cinema.controller;

import com.github.pagehelper.PageInfo;
import com.woniu.woniuticket.cinema.client.OrderClient;
import com.woniu.woniuticket.cinema.pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class orderController {
    @Autowired
    OrderClient orderClient;

    /**
     *  查询所有订单
     * @return 所有订单集合
     */
    @GetMapping("/order")
    public ModelAndView testOrder(){
        ModelAndView mv=new ModelAndView();
        PageInfo<Order> pageInfo=orderClient.getAllOrder();
        mv.setViewName("order-list");
        mv.addObject("pageInfo",pageInfo);
        return mv;
    }


}
