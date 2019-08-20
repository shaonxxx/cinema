package com.woniu.woniuticket.cinema.dao;

import com.woniu.woniuticket.cinema.pojo.Cinema;

public interface CinemaMapper {
    int deleteByPrimaryKey(Integer cinemaId);

    int insert(Cinema record);

    int insertSelective(Cinema record);

    Cinema selectByPrimaryKey(Integer cinemaId);

    int updateByPrimaryKeySelective(Cinema record);

    int updateByPrimaryKey(Cinema record);
}