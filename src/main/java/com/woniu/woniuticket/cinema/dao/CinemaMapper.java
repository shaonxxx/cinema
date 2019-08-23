package com.woniu.woniuticket.cinema.dao;

import com.woniu.woniuticket.cinema.pojo.Cinema;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CinemaMapper {
    int deleteByPrimaryKey(Integer cinemaId);

    int insert(Cinema record);

    int insertSelective(Cinema record);

    Cinema selectByPrimaryKey(Integer cinemaId);

    int updateByPrimaryKeySelective(Cinema record);

    int updateByPrimaryKey(Cinema record);

    //查询所有影院，可按影院名称查询
    List<Cinema> selectAllCinema(@Param("cinema") Cinema cinema,@Param("currentPage") Integer currentPage,
                                 @Param("pageSize") Integer pageSize);

    //根据ID查询影院
    Cinema selectCinemaById(Integer cinemaId);

    //根据名字查找影院
    Cinema selectCinemaByName(String ciname);

    //删除影院
    void deleteCinemaById(Integer cinemaId);
}