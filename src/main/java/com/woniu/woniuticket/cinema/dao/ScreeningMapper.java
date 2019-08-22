package com.woniu.woniuticket.cinema.dao;

import com.woniu.woniuticket.cinema.pojo.Screening;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ScreeningMapper {
    List<Screening> SelectByNewDate(@Param("hid") Integer hid,@Param("date") String date);

    int deleteByPrimaryKey(Integer chipId);

    int insert(Screening record);

    int insertSelective(Screening record);

    Screening selectByPrimaryKey(Integer chipId);

    int updateByPrimaryKeySelective(Screening record);

    int updateByPrimaryKey(Screening record);

}