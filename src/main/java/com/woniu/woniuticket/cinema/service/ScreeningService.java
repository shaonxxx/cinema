package com.woniu.woniuticket.cinema.service;


import com.woniu.woniuticket.cinema.pojo.Screening;

import java.util.List;

public interface ScreeningService {

    /*public List<ScreeningService> findScreeningByCondition(FilmVO screeningVO, Integer currentPage, Integer pageSize);*/

    public List<Screening> findScreeningByNowDate(Integer hid);

}
