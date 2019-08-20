package com.woniu.woniuticket.cinema.dao;

import com.woniu.woniuticket.cinema.pojo.CinemaComment;

public interface CinemaCommentMapper {
    int deleteByPrimaryKey(Integer cinemaCommentId);

    int insert(CinemaComment record);

    int insertSelective(CinemaComment record);

    CinemaComment selectByPrimaryKey(Integer cinemaCommentId);

    int updateByPrimaryKeySelective(CinemaComment record);

    int updateByPrimaryKey(CinemaComment record);
}