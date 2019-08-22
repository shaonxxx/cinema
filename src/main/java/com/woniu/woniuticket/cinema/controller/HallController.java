package com.woniu.woniuticket.cinema.controller;

import com.woniu.woniuticket.cinema.pojo.Hall;
import com.woniu.woniuticket.cinema.service.HallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HallController {

    @Autowired
    private HallService hallService;

    /**
     * 根据ID查找放映厅
     * @param hid
     * @return
     */
    @GetMapping("/hall/{hid}")
    public Map findHallById(@PathVariable(value = "hid") Integer hid){
        Map result=new HashMap();
        Hall hall = hallService.findHallById(hid);
        result.put("hall",hall);
        System.out.println(result);
        return result;
    }
}
