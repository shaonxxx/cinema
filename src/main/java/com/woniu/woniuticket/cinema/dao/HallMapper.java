package com.woniu.woniuticket.cinema.dao;

import com.woniu.woniuticket.cinema.pojo.Hall;
import com.woniu.woniuticket.cinema.pojo.Order;
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
    // 根据排片id查询是否有订单
    List<Order> selectOrderByChipId(Integer chipId);
    // 通过排片id查询出排片对象
    Screening selectScreeningByChipId(Integer chipId);
    // 通过影厅Id开启影厅
    int updateHallStateByHallId(@Param("hallId") Integer hallId,@Param("hallState") Integer hallState);
}