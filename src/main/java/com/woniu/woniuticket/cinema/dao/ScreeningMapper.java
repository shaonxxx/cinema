package com.woniu.woniuticket.cinema.dao;

import com.woniu.woniuticket.cinema.pojo.Screening;

public interface ScreeningMapper {
    int deleteByPrimaryKey(Integer chipId);

    int insert(Screening record);

    int insertSelective(Screening record);

    Screening selectByPrimaryKey(Integer chipId);

    int updateByPrimaryKeySelective(Screening record);

    int updateByPrimaryKey(Screening record);
}