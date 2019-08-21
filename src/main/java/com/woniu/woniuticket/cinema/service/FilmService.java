package com.woniu.woniuticket.cinema.service;

import com.github.pagehelper.PageInfo;
import com.woniu.woniuticket.cinema.pojo.Film;

public interface FilmService {

    public PageInfo<Film> selectAllFilm();

    public Film selectFilmByfid(Integer fid);

    public int addFilm(Film film);

    int deleteFilmByfid(Integer fid);

    int updateFilmBySelective(Film film);
}
