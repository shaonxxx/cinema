package com.woniu.woniuticket.cinema.controller;


import com.github.pagehelper.PageInfo;
import com.woniu.woniuticket.cinema.execption.FilmException;
import com.woniu.woniuticket.cinema.pojo.Film;
import com.woniu.woniuticket.cinema.pojo.Result;
import com.woniu.woniuticket.cinema.service.FilmService;
import com.woniu.woniuticket.cinema.vo.FilmVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public Result getFilmByfid( Integer id){
        Result result = new Result();
        Film film = filmService.selectFilmByfid(id);
        result.setData(film);
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
    public Result<PageInfo> findfilmByCondition(@RequestParam(value = "filmVO",required = false)FilmVO filmVO,@RequestParam(value ="currentPage",defaultValue = "1")Integer currentPage,
                                   @RequestParam(value = "pageSize",defaultValue = "10")Integer pagesize){
        Result result = new Result();
        List<Film> films = filmService.findFilmByCondition(filmVO, currentPage, pagesize);
        PageInfo<Film> pageInfo = new PageInfo<Film>(films);
        result.setCode("200");
        result.setMessage("查询成功");
        result.setData(pageInfo);
        return result;
    }

    /**
     * 添加影片
     * @param film
     * @return
     */
    @PostMapping("/add")
    public Result addFilm(Film film){
        Result result = new Result();
        try {
            filmService.addFilm(film);
            result.setCode("200");
            result.setMessage("添加成功");
        } catch (FilmException e) {
            e.printStackTrace();
            result.setCode("500");
            result.setMessage(e.getMessage());
        }
        return result;
    }

}
