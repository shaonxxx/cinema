package com.woniu.woniuticket.cinema.service;

import com.woniu.woniuticket.cinema.pojo.Hall;
import com.woniu.woniuticket.cinema.pojo.Result;

import java.util.List;

public interface HallService {

    Hall findHallById(Integer hid);
    List<Hall> findAllHall();
    int addHall(Hall hall);
    // 根据排片Id关闭或打开放映厅状态
    Result updateHallStateByChipId(Integer chipId, String state);
}
