package com.woniu.woniuticket.cinema.dao;

import com.woniu.woniuticket.cinema.pojo.screening;

public interface screeningMapper {
    int deleteByPrimaryKey(Integer chipId);

    int insert(screening record);

    int insertSelective(screening record);

    screening selectByPrimaryKey(Integer chipId);

    int updateByPrimaryKeySelective(screening record);

    int updateByPrimaryKey(screening record);
}