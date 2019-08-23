package com.woniu.woniuticket.cinema.controller;


import com.github.pagehelper.PageInfo;
import com.woniu.woniuticket.cinema.dto.ScreeningDTO;
import com.woniu.woniuticket.cinema.pojo.Screening;
import com.woniu.woniuticket.cinema.service.ScreeningService;
import com.woniu.woniuticket.cinema.vo.ScreeningVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

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

    @GetMapping("/queryScreening")
    public ModelAndView queryScreening(ScreeningVO screeningVO, @RequestParam(value = "currentPage",defaultValue = "1")Integer currentPage,
                                       @RequestParam(value = "pageSize",defaultValue = "5")Integer pageSize){
        ModelAndView mv = new ModelAndView("hall-details");
        List<ScreeningDTO> list = screeningService.findScreeningByCondition(screeningVO, currentPage, pageSize);
        PageInfo<ScreeningDTO> pageInfo = new PageInfo<>(list);
        mv.addObject("pageInfo",pageInfo);
        return mv;
    }
}
