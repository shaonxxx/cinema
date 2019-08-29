package com.woniu.woniuticket.cinema.controller;

import com.woniu.woniuticket.cinema.pojo.Hall;
import com.woniu.woniuticket.cinema.pojo.Result;
import com.woniu.woniuticket.cinema.service.HallService;
import com.woniu.woniuticket.cinema.service.ScreeningService;
import com.woniu.woniuticket.cinema.utils.HallUtil;
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
    @Autowired
    private ScreeningService screeningService;


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

    @GetMapping("/findhallById")
    public ModelAndView findHallByOneId(@RequestParam(value = "hid",required = false) Integer hid){
        ModelAndView mv=new ModelAndView();
        Hall hall = hallService.findHallById(hid);
        String[] sts=(hall.getSeatmap()).split(",");
        mv.addObject("hall",hall);
        mv.addObject("sts",sts);
        mv.setViewName("hall-details");
        return mv;
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
        modelAndView.setViewName("cate");
        return modelAndView;
    }


    /**
     * 放映厅详情页面跳转
     * @param hid
     * @return
     */
    @GetMapping("/details/{hid}")
    public ModelAndView Test01(@PathVariable("hid") Integer hid){
        ModelAndView modelAndView=new ModelAndView();
        Hall hall = hallService.findHallById(hid);
        modelAndView.addObject("hall",hall);
        modelAndView.setViewName("hall-details");
        return modelAndView;
    }

    /**
     *  放映厅添加排片页面跳转
     * @param hid
     * @return
     */
    @GetMapping("/skipScreening")
    public ModelAndView skipScreening(Integer hid){
        ModelAndView modelAndView=new ModelAndView();
        Hall hall = hallService.findHallById(hid);
        modelAndView.addObject("hall",hall);
        modelAndView.setViewName("addScreening");
        return modelAndView;
    }


    @PostMapping("/addhall")
    @ResponseBody
    public Result addHall(@RequestBody  Hall hall){
        Result result=new Result();
        String seats = HallUtil.seattransmap(hall.getSeats());
        hall.setSeatmap(seats);
        hall.setSeats(HallUtil.seat(seats));
        hall.setSeatNum(HallUtil.seattotal(seats));
        hallService.addHall(hall);
        result.setCode("0");
        result.setMessage("成功");
        result.setData(hall);
        return result;
    }

}
