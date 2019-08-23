package com.woniu.woniuticket.cinema.controller;


import com.woniu.woniuticket.cinema.pojo.Screening;
import com.woniu.woniuticket.cinema.service.ScreeningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ScreeningController {

    @Autowired
    ScreeningService screeningService;
    /**
     *  根据放映查找拍片
     * @param  hid 放映厅编号
     * @return 当前时间有效拍片信息。
     */
    @GetMapping("/screening/{hid}")
    public List<Screening> getScreeningByNewDate(@PathVariable("hid") Integer hid){
        return screeningService.findScreeningByNowDate(hid);
    }


}
