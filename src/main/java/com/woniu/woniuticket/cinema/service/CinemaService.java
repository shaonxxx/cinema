package com.woniu.woniuticket.cinema.service;

import com.woniu.woniuticket.cinema.pojo.Cinema;

import java.util.List;

public interface CinemaService {

  List<Cinema> findAllCinema(Cinema cinema,Integer currentPage,Integer pageSize);

  Cinema findCinemaById(Integer cid);

  //添加
  void addCinema(Cinema cinema);

  //修改
  void modifyCinema(Cinema cinema);

  void removeCinema(Integer cinemaId);
}
