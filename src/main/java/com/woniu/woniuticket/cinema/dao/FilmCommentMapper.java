package com.woniu.woniuticket.cinema.dao;

import com.woniu.woniuticket.cinema.pojo.FilmComment;

public interface FilmCommentMapper {
    int deleteByPrimaryKey(Integer filmCommentId);

    int insert(FilmComment record);

    int insertSelective(FilmComment record);

    FilmComment selectByPrimaryKey(Integer filmCommentId);

    int updateByPrimaryKeySelective(FilmComment record);

    int updateByPrimaryKey(FilmComment record);
}