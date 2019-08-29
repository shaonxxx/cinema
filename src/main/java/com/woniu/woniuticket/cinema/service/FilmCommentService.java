package com.woniu.woniuticket.cinema.service;

import com.woniu.woniuticket.cinema.pojo.FilmComment;

import java.util.List;

public interface FilmCommentService {

    public void addFilmComment(FilmComment filmComment);

    public Double selectAvgScore(Integer filmId);

    public void removeFilmCommentByNickName(Integer filmId);

    public List<FilmComment> findFilmCommentsByFilmId(Integer filmId,Integer currentPage, Integer pageSize);
}
