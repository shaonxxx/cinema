package com.woniu.woniuticket.cinema.service.serviceimpl;

import com.github.pagehelper.PageInfo;
import com.woniu.woniuticket.cinema.dao.FilmMapper;
import com.woniu.woniuticket.cinema.pojo.Film;
import com.woniu.woniuticket.cinema.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FilmServiceImpl implements FilmService {

    @Autowired
    FilmMapper filmMapper;


    @Override
    public PageInfo<Film> selectAllFilm() {

        return null;
    }

    @Override
    public Film selectFilmByfid(Integer fid) {
        return filmMapper.selectByPrimaryKey(fid);
    }

    @Override
    public int addFilm(Film film) {

        return filmMapper.insertSelective(film);
    }

    @Override
    public int deleteFilmByfid(Integer fid) {
        return filmMapper.deleteByPrimaryKey(fid);
    }

    @Override
    public int updateFilmBySelective(Film film) {
        return filmMapper.updateByPrimaryKeySelective(film);
    }
}
