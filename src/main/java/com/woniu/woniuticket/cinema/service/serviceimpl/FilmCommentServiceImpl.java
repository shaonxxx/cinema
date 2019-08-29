package com.woniu.woniuticket.cinema.service.serviceimpl;

import com.woniu.woniuticket.cinema.dao.FilmCommentMapper;
import com.woniu.woniuticket.cinema.execption.FilmCommentException;
import com.woniu.woniuticket.cinema.pojo.FilmComment;
import com.woniu.woniuticket.cinema.service.FilmCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmCommentServiceImpl implements FilmCommentService {

    @Autowired
    FilmCommentMapper filmCommentMapper;
    @Override
    public void addFilmComment(FilmComment filmComment) {
        try {
            filmCommentMapper.insertSelective(filmComment);
        } catch (Exception e) {
            e.printStackTrace();
            throw new FilmCommentException("评论失败");
        }
    }

    @Override
    public Double selectAvgScore(Integer filmId) {
        try {
            return filmCommentMapper.selectAvgScore(filmId);
        } catch (Exception e) {
            throw new FilmCommentException("获取平均值失败");
        }

    }

    @Override
    public void removeFilmCommentByNickName(Integer filmCommentId) {
        try {
            filmCommentMapper.deleteByPrimaryKey(filmCommentId);
        } catch (Exception e) {
            throw new FilmCommentException("删除失败");
        }
    }

    @Override
    public List<FilmComment> findFilmCommentsByFilmId(Integer filmId,Integer currentPage,Integer pageSize) {
        filmCommentMapper.selectFilmCommentsByFilmCommentId(filmId,currentPage,pageSize);
        return null;
    }
}
