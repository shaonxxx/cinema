package com.woniu.woniuticket.cinema.dao;

import com.woniu.woniuticket.cinema.pojo.Film;
import com.woniu.woniuticket.cinema.vo.FilmVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FilmTest {

    @Autowired
    FilmMapper filmMapper;

    @Test
    public void testselectFilmByCondition(){
        FilmVO filmVO=new FilmVO();
        filmVO.setFilmName("精武门");
        List<Film> films = filmMapper.selectFilmByCondition(filmVO, 1, 5);
        System.out.println(films);
    }

    @Test
    public void testdeleteFilms(){
        List<String> ids = new ArrayList<>();
        ids.add("13");
        ids.add("14");

        filmMapper.deleteFilms(ids);
    }

}
