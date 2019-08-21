package com.woniu.woniuticket.cinema.controller;


import com.github.pagehelper.PageInfo;
import com.woniu.woniuticket.cinema.pojo.Film;
import com.woniu.woniuticket.cinema.service.FilmService;
import com.woniu.woniuticket.cinema.vo.FilmVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class FilmController {
    @Autowired
    FilmService filmService;

    /**
     * 根据影片id查找影片
     * @param id
     * @return
     */
    @GetMapping("/film")
    public Map getFilmByfid( Integer id){
        Map result = new HashMap();
        Film film = filmService.selectFilmByfid(id);
        result.put("film",film);
        return result;
    }

    /**
     * 条件查询影片，没有调价则查询所有影片
     * @param filmVO
     * @param currentPage
     * @param pagesize
     * @return
     */
    @GetMapping("/filmlist")
    public Map findfilmByCondition(@RequestParam(value = "filmVO",required = false)FilmVO filmVO,@RequestParam(value ="currentPage",defaultValue = "1")Integer currentPage,
                                   @RequestParam(value = "pageSize",defaultValue = "10")Integer pagesize){
        Map map = new HashMap();
        List<Film> films = filmService.findFilmByCondition(filmVO, currentPage, pagesize);
        PageInfo<Film> pageInfo = new PageInfo<Film>(films);
        map.put("pageInfo",pageInfo);
        return map;
    }


    @PostMapping("/add")
    public Map addFilm(Film film){
        Map result = new HashMap();
        try {
//            filmService.addFilm(film);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


}
