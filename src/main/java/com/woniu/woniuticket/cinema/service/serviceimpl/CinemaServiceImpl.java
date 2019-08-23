package com.woniu.woniuticket.cinema.service.serviceimpl;

import com.woniu.woniuticket.cinema.dao.CinemaMapper;
import com.woniu.woniuticket.cinema.execption.CinemaException;
import com.woniu.woniuticket.cinema.pojo.Cinema;
import com.woniu.woniuticket.cinema.service.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CinemaServiceImpl implements CinemaService {

    @Autowired
    private CinemaMapper cinemaMapper;

    @Override
    public List<Cinema> findAllCinema(Cinema cinema, Integer currentPage, Integer pageSize) {
        try {
            return cinemaMapper.selectAllCinema(cinema, currentPage, pageSize);
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new CinemaException("查询失败");
        }


    }

    @Override
    public Cinema findCinemaById(Integer cid) {
        try {
            return cinemaMapper.selectCinemaById(cid);
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new CinemaException("查询失败");
        }

    }

    @Override
    public void addCinema(Cinema cinema) {
        Cinema c = cinemaMapper.selectCinemaByName(cinema.getCiname());
        if(c!=null){
            throw new CinemaException("影院已存在");
        }
        cinemaMapper.insertSelective(cinema);

    }

    @Override
    public void modifyCinema(Cinema cinema) {
        try {
            cinemaMapper.updateByPrimaryKeySelective(cinema);
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new CinemaException("修改失败");
        }
    }

    @Override
    public void removeCinema(Integer cinemaId) {
        try {
            cinemaMapper.deleteCinemaById(cinemaId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CinemaException("删除失败");
        }
    }

}
