package com.woniu.woniuticket.cinema.controller;

import com.woniu.woniuticket.cinema.pojo.Hall;
import com.woniu.woniuticket.cinema.service.HallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin
public class HallController {

    @Autowired
    private HallService hallService;

    /**
     * 根据ID查找放映厅
     * @param hid
     * @return
     */
    @GetMapping("/hall/{hid}")
    @ResponseBody
    public Map findHallById(@PathVariable(value = "hid") Integer hid){
        Map result=new HashMap();
        Hall hall = hallService.findHallById(hid);
        result.put("hall",hall);
        System.out.println(result);
        return result;
    }

    /**
     * 查找所有放映厅
     * @return 放映厅列表
     */
    @GetMapping("/hall")
    public ModelAndView findAllHall(){
        ModelAndView modelAndView=new ModelAndView();
        Map result=new HashMap();
        List<Hall> halls=hallService.findAllHall();
        result.put("halls",halls);
        modelAndView.addObject("halls",halls);
        modelAndView.setViewName("/cate.html");
        return modelAndView;
    }


    @GetMapping("/test01/{fid}")
    public ModelAndView Test01(@PathVariable("fid") Integer fid){
        ModelAndView modelAndView=new ModelAndView();
        System.out.println("进入test");
        modelAndView.addObject("msg","testtestest");
        modelAndView.setViewName("/admin-cate.html");
        return modelAndView;
    }
}
