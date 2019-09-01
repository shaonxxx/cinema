package com.woniu.woniuticket.cinema.service;


import com.woniu.woniuticket.cinema.dto.ScreeningDTO;
import com.woniu.woniuticket.cinema.pojo.Screening;
import com.woniu.woniuticket.cinema.vo.ScreeningVO;

import java.util.List;

public interface ScreeningService {

    /*public List<ScreeningService> findScreeningByCondition(FilmVO screeningVO, Integer currentPage, Integer pageSize);*/

    public List<Screening> findScreeningByNowDate(Integer hid);

    public List<ScreeningDTO> findScreeningByCondition(ScreeningVO screeningVO, Integer currentPage, Integer pagesize);

    public int addScreening(Screening screening);

    public int removeScreenings(List<String> ids);

    public int updateScreening(Screening screening);

    List<Screening> findScreeningByhid(Integer hid);

    List<ScreeningDTO> findScreeningByFilmId(Integer filmId);

    Screening findScreenBychipid(Integer chipid);

    // 删除排片
    Result deleteScreeningByChipId(Integer chipId);
}
