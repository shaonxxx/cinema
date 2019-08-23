package com.woniu.woniuticket.cinema.service;


import com.woniu.woniuticket.cinema.dto.ScreeningDTO;
import com.woniu.woniuticket.cinema.pojo.Screening;

import java.util.List;

public interface ScreeningService {

    /*public List<ScreeningService> findScreeningByCondition(FilmVO screeningVO, Integer currentPage, Integer pageSize);*/

    public List<Screening> findScreeningByNowDate(Integer hid);

    public List<Screening> findScreeningByCondition(ScreeningDTO screeningDTO, Integer currentPage, Integer pagesize);

    public int addScreening(Screening screening);

    public int removeScreenings(List<String> ids);

    public int updateScreening(Screening screening);
}
