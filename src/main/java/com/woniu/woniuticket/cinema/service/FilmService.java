package com.woniu.woniuticket.cinema.service;

import com.woniu.woniuticket.cinema.pojo.Film;
import com.woniu.woniuticket.cinema.vo.FilmVO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface FilmService {


    public List<Film> findFilmByCondition(FilmVO filmVO, Integer currentPage, Integer pageSize);

    public Film selectFilmByfid(Integer fid);

    void addFilm(Film film);

    //批量删除
    void removeFilms(List<String> ids);

    List<Film> selectRandom(Integer num);

    List<Film> selectHot(Integer currentPage,Integer pageSize);

    List<Film> selectNew(Integer currentPage,Integer pageSize);

    void add(Film film);

    List<Film> findAll();

    /**
     * 关键字查找  通过Elasticsearch
     * @param filmVO
     * @return
     */
    Page<Film> findByKeyword(FilmVO filmVO);
}
