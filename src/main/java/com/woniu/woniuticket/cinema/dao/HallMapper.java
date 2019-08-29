package com.woniu.woniuticket.cinema.dao;

import com.woniu.woniuticket.cinema.pojo.Hall;

import java.util.List;

public interface HallMapper {
    int deleteByPrimaryKey(Integer hallId);

    int insert(Hall record);

    int insertSelective(Hall record);

    Hall selectByPrimaryKey(Integer hallId);

    int updateByPrimaryKeySelective(Hall record);

    int updateByPrimaryKey(Hall record);

    List<Hall> selectAllHall();

}