package com.woniu.woniuticket.cinema.service.serviceimpl;

import com.woniu.woniuticket.cinema.dao.FilmMapper;
import com.woniu.woniuticket.cinema.execption.FilmException;
import com.woniu.woniuticket.cinema.pojo.Film;
import com.woniu.woniuticket.cinema.service.FilmService;
import com.woniu.woniuticket.cinema.vo.FilmVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmServiceImpl implements FilmService {

    @Autowired
    FilmMapper filmMapper;


    @Override
    public List<Film> findFilmByCondition(FilmVO filmVO, Integer currenPage, Integer pageSize) {

        return filmMapper.selectFilmByCondition(filmVO,currenPage,pageSize);
    }

    @Override
    public Film selectFilmByfid(Integer fid) {
        return filmMapper.selectByPrimaryKey(fid);
}

    @Override
    public void addFilm(Film film) {
        Film findFilm = filmMapper.selectFilmByName(film.getFilmName());
        System.out.println(findFilm);
        if(findFilm!=null){
            throw new FilmException("影片已存在");
        }
        filmMapper.insertSelective(film);
    }

    @Override
    public void removeFilms(List<String> ids) {
        filmMapper.deleteFilms(ids);
    }

    @Override
    public List<Film> selectRandom(Integer num) {
        return null;
    }

    @Override
    public List<Film> selectHot(Integer num) {
        return filmMapper.selectHot(num);
    }

    @Override
    public List<Film> selectNew(Integer num) {
        return filmMapper.selectNew(num);
    }

    @Override
    public void add(Film film) {
        filmMapper.insertSelective(film);
    }


}
