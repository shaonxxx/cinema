package com.woniu.woniuticket.cinema.service;

import com.woniu.woniuticket.cinema.pojo.Film;
import com.woniu.woniuticket.cinema.vo.FilmVO;

import java.util.List;

public interface FilmService {


    public List<Film> findFilmByCondition(FilmVO filmVO, Integer currentPage, Integer pageSize);

    public Film selectFilmByfid(Integer fid);


}
