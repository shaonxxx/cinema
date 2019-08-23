package com.woniu.woniuticket.cinema.service.serviceimpl;

import com.woniu.woniuticket.cinema.dao.HallMapper;
import com.woniu.woniuticket.cinema.pojo.Hall;
import com.woniu.woniuticket.cinema.service.HallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HallServiceImpl implements HallService {

    @Autowired
    private HallMapper hallMapper;

    @Override
    public Hall findHallById(Integer hid) {
        return hallMapper.selectByPrimaryKey(hid);
    }

    @Override
    public List<Hall> findAllHall() {

        return hallMapper.selectAllHall();
    }
}
