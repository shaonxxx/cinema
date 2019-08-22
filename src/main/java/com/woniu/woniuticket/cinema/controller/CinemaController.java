package com.woniu.woniuticket.cinema.controller;

import com.github.pagehelper.PageInfo;
import com.woniu.woniuticket.cinema.pojo.Cinema;
import com.woniu.woniuticket.cinema.pojo.Result;
import com.woniu.woniuticket.cinema.service.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CinemaController {

    @Autowired
    private CinemaService cinemaService;

    /**
     * 查询影院，如果没有条件，默认查所有
     * @param cinema
     * @param currentPage
     * @param pageSiez
     * @return
     */
    @GetMapping("/cinemalist")
    public Result findAllCinema(@RequestParam(value="cinema",required = false) Cinema cinema,
                                @RequestParam(value="currentPage",defaultValue = "1") Integer currentPage,
                                @RequestParam(value="pageSize",defaultValue = "10") Integer pageSiez){

        Result result=new Result();
        /*Cinema cinema=new Cinema();
        cinema.setCiname("阳光");
        cinema.setState(1);*/
        List<Cinema> list = cinemaService.findAllCinema(cinema, currentPage, pageSiez);
        PageInfo<Cinema> pageInfo=new PageInfo<>(list);
        result.setCode("200");
        result.setMessage("查询成功");
        result.setData(pageInfo);
        return result;
    }

    /**
     * 根据ID查询电影院
     * @param cid
     * @return
     */
    @GetMapping("/cinema/{cid}")
    public Map findCinemaById(@PathVariable(value = "cid") Integer cid){
        Map result=new HashMap();
        Cinema cinema = cinemaService.findCinemaById(cid);
        result.put("cinema",cinema);
        return  result;
    }

    /**
     * 添加影院
     * @param cinema
     * @return
     */
    @GetMapping("/addcinema")
    public Result addCinema(@RequestParam(value="cinema",required = false) Cinema cinema){
        Result result=new Result();
        try {
            cinemaService.addCinema(cinema);
            result.setCode("200");
            result.setMessage("添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode("500");
            result.setMessage(e.getMessage());
        }
        return result;
    }

    /**
     * 影院的信息修改，找出对象修改信息
     * @param cinema
     * @return
     */
    @GetMapping("/modifycinema")
    public Result modifyCinema(@RequestParam(value = "cinema",required = false) Cinema cinema){
        Result result=new Result();
        Cinema cin = cinemaService.findCinemaById(cinema.getCinemaId());
        try {
            cinemaService.modifyCinema(cin);
            result.setCode("200");
            result.setMessage("修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode("500");
            result.setMessage(e.getMessage());
        }
        return result;
    }

    @GetMapping("/delete/{cid}")
    public Result removeCinema(@PathVariable(value = "cid") Integer cid){
        Result result=new Result();
        try {
            cinemaService.removeCinema(cid);
            result.setCode("200");
            result.setMessage("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode("500");
            result.setMessage(e.getMessage());
        }
        return result;
    }
}
