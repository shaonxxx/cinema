package com.woniu.woniuticket.cinema.config;

import com.woniu.woniuticket.cinema.pojo.Film;
import com.woniu.woniuticket.cinema.repository.FilmRepository;
import com.woniu.woniuticket.cinema.service.FilmService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

//@Configuration
public class ESConfig implements InitializingBean {

    @Autowired
    FilmRepository filmRepository;

    @Autowired
    FilmService filmService;

    @Override
    public void afterPropertiesSet() throws Exception {
        List<Film> films = filmService.findAll();
        filmRepository.saveAll(films);
    }
}
