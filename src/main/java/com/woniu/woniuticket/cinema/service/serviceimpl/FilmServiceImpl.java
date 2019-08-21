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
        if(findFilm!=null){
            throw new FilmException("影片已存在");
        }
        filmMapper.insertSelective(film);
    }

}
