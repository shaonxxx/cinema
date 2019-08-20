package com.woniu.woniuticket.cinema.controller;


import com.woniu.woniuticket.cinema.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilmController {
    @Autowired
    FilmService filmService;

    @GetMapping("/film")
    public Object getFilmByfid( Integer fid){
        System.out.println(fid);
        return filmService.selectFilmByfid(fid);
    }
}
