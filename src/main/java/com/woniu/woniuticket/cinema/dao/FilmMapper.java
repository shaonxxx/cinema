package com.woniu.woniuticket.cinema.dao;

import com.woniu.woniuticket.cinema.pojo.Film;

public interface FilmMapper {
    int deleteByPrimaryKey(Integer filmId);

    int insert(Film record);

    int insertSelective(Film record);

    Film selectByPrimaryKey(Integer filmId);

    int updateByPrimaryKeySelective(Film record);

    int updateByPrimaryKey(Film record);


}