package com.woniu.woniuticket.cinema.dao;

import com.woniu.woniuticket.cinema.pojo.Hall;
import com.woniu.woniuticket.cinema.pojo.Screening;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HallMapper {
    int deleteByPrimaryKey(Integer hallId);

    int insert(Hall record);

    int insertSelective(Hall record);

    Hall selectByPrimaryKey(Integer hallId);

    int updateByPrimaryKeySelective(Hall record);

    int updateByPrimaryKey(Hall record);

    List<Hall> selectAllHall();
    // 通过影厅Id查询出排片对象
    Screening selectScreeningByChipId(Integer hallId);
    // 通过影厅Id开启影厅
    int updateHallStateByHallId(@Param("hallId") Integer hallId,@Param("hallState") Integer hallState);
}