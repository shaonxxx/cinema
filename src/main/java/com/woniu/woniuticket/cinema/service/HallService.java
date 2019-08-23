package com.woniu.woniuticket.cinema.service;

import com.woniu.woniuticket.cinema.pojo.Hall;

import java.util.List;

public interface HallService {

    Hall findHallById(Integer hid);
    List<Hall> findAllHall();
}
