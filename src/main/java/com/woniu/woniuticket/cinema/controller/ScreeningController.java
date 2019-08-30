package com.woniu.woniuticket.cinema.controller;


import com.github.pagehelper.PageInfo;
import com.woniu.woniuticket.cinema.dto.ScreeningDTO;
import com.woniu.woniuticket.cinema.pojo.Screening;
import com.woniu.woniuticket.cinema.service.ScreeningService;
import com.woniu.woniuticket.cinema.vo.ScreeningVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ScreeningController {

    @Autowired
    ScreeningService screeningService;

    /**
     *  根据放映查找拍片(暂时没用)
     * @param  hid 放映厅编号
     * @return 当前时间有效拍片信息。
     */
    @GetMapping("/screening/{hid}")
    public ModelAndView getScreeningByNewDate(@PathVariable("hid") Integer hid){
        List<Screening> screenings=screeningService.findScreeningByNowDate(hid);
        ModelAndView mv = new ModelAndView();
        mv.addObject("screenings",screenings);
        mv.setViewName("chall-details");
        return mv ;
    }

    /**
     * @param screeningVO 查询条件封装类VO
     * @param currentPage 当前页码
     * @param pageSize 每页能获取的最大数量
     * @return ModelAndView pageInfo:条件查询集合
     */
    @GetMapping("/queryScreening")
    public ModelAndView queryScreening(ScreeningVO screeningVO,
                                       @RequestParam(value = "resUrl") String resUrl,
                                       @RequestParam(value = "currentPage",defaultValue = "1")Integer currentPage,
                                       @RequestParam(value = "pageSize",defaultValue = "5")Integer pageSize){
        ModelAndView mv = new ModelAndView(resUrl);
        System.out.println(screeningVO.getFilmId());
        List<ScreeningDTO> list = screeningService.findScreeningByCondition(screeningVO, currentPage, pageSize);
        PageInfo<ScreeningDTO> pageInfo = new PageInfo<>(list);
        mv.addObject("pageInfo",pageInfo);
        return mv;
    }



    /**
     * 添加排片信息
     * @param screening
     * @return
     */
    @PostMapping("/addScreening")
    public Map addScreening(Screening screening){
        Map result = new HashMap();
        try {
            screeningService.addScreening(screening);
            result.put("code",200);
            result.put("message","添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            result.put("code",500);
            result.put("message",e.getMessage());
        }
        return result;
    }

    @PutMapping("/updateScreening")
    public Map updateScreeening(Screening screening){
        Map result = new HashMap();
        try {
            screeningService.updateScreening(screening);
            result.put("code",200);
            result.put("message","更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            result.put("code",500);
            result.put("message",e.getMessage());
        }
        return result;
    }
}
