package com.woniu.woniuticket.cinema.config;

import com.woniu.woniuticket.cinema.repository.FilmRepository;
import com.woniu.woniuticket.cinema.service.FilmService;


public class ESConfig{


    FilmRepository filmRepository;

    FilmService filmService;

    /*@Override
    public void afterPropertiesSet() throws Exception {
        List<Film> films = filmService.findAll();
        filmRepository.saveAll(films);
    }*/
}
