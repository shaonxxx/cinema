package com.woniu.woniuticket.cinema.service;

import com.woniu.woniuticket.cinema.pojo.Film;
import com.woniu.woniuticket.cinema.vo.FilmVO;

import java.util.List;

public interface FilmService {


    public List<Film> findFilmByCondition(FilmVO filmVO, Integer currentPage, Integer pageSize);

    public Film selectFilmByfid(Integer filmId);

    void addFilm(Film film);

    //批量删除
    void removeFilms(List<String> ids);

    List<Film> selectRandom(Integer num);

    List<Film> selectHot(Integer currentPage,Integer pageSize);

    List<Film> selectNew(Integer currentPage,Integer pageSize);

    void add(Film film);

}
