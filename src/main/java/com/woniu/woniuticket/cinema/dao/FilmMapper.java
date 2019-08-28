package com.woniu.woniuticket.cinema.dao;

import com.woniu.woniuticket.cinema.pojo.Film;
import com.woniu.woniuticket.cinema.vo.FilmVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface FilmMapper {
    int deleteByPrimaryKey(Integer filmId);

    int insert(Film record);

    int insertSelective(Film record);

    Film selectByPrimaryKey(Integer filmId);

    int updateByPrimaryKeySelective(Film record);

    int updateByPrimaryKey(Film record);

    //条件查询影片，后台分页
    List<Film> selectFilmByCondition(@Param("filmVO")FilmVO filmVO,@Param("currentPage")Integer currentPage,
                                     @Param("pageSzie")Integer pageSize);

   Film selectFilmByName(String filmName);

    void deleteFilms(List<String> ids);

    @Select("select count(*) from film")
    Integer selectCount();

    @Select("select * from film limit #{0},1")
    Film selectRandom(Integer num);

    List<Film> selectHot(@Param("currentPage") Integer currentPage,@Param("pageSize") Integer pageSize);

    List<Film> selectNew(@Param("currentPage") Integer currentPage,@Param("pageSize") Integer pageSize);
}