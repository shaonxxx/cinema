package com.woniu.woniuticket.cinema.controller;


import com.woniu.woniuticket.cinema.pojo.Film;
import com.woniu.woniuticket.cinema.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class FilmController {

    @Autowired
    FilmService filmService;

    @GetMapping("/film/{fid}")
    public Object getFilmByfid(@PathVariable Integer fid){
        System.out.println("执行查询");
        return filmService.selectFilmByfid(fid);
    }
    @PostMapping("/film")
    public int addFilm(Film film){
        System.out.println("执行添加");
        return filmService.addFilm(film);
    }
    @DeleteMapping("/film")
    public int deleteFilmByfid(Integer fid){
        System.out.println("执行删除");
        return filmService.deleteFilmByfid(fid);
    }
    @PutMapping("/film")
    public int updateFilmBySelective(Film film){
        System.out.println("执行修改");
        return filmService.updateFilmBySelective(film);
    }
}
