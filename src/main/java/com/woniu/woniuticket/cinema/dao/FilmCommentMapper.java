package com.woniu.woniuticket.cinema.dao;

import com.woniu.woniuticket.cinema.pojo.FilmComment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FilmCommentMapper {
    int deleteByPrimaryKey(Integer filmCommentId);

    int insert(FilmComment record);
    Double selectAvgScore (Integer filmId);

    int insertSelective(FilmComment record);

    FilmComment selectByPrimaryKey(Integer filmCommentId);

    int updateByPrimaryKeySelective(FilmComment record);

    int updateByPrimaryKey(FilmComment record);

    List<FilmComment> selectFilmCommentsByFilmCommentId(@Param("filmCommentId") Integer filmCommentId,
                                                        @Param("currentPage") Integer currentPage,
                                                        @Param("pageSize") Integer pageSize);
}