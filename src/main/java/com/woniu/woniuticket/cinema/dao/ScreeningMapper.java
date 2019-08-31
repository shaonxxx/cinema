package com.woniu.woniuticket.cinema.dao;

import com.woniu.woniuticket.cinema.dto.ScreeningDTO;
import com.woniu.woniuticket.cinema.pojo.Screening;
import com.woniu.woniuticket.cinema.vo.ScreeningVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ScreeningMapper {
    List<Screening> SelectByNewDate(@Param("hid") Integer hid,@Param("date") String date);

    int deleteByPrimaryKey(Integer chipId);

    /**
     * 添加排片信息
     * @param record
     * @return
     */
    int insert(Screening record);

    int insertSelective(Screening record);

    Screening selectByPrimaryKey(Integer chipId);

    int updateByPrimaryKeySelective(Screening record);

    int updateByPrimaryKey(Screening record);

    /**
     * 条件查询排片信息
     * @param screeningVO
     * @param currentPage
     * @param pagesize
     * @return
     */
    List<Screening> selectScreeningByCondition(@Param("screeningVO") ScreeningVO screeningVO,
                                               @Param("currentPage") Integer currentPage,
                                               @Param("pageSize") Integer pagesize);

    List<Screening> selectScreeningsByHallId(Integer hallId);

    void deleteScreenings(List<String> ids);

    List<Screening> selectScreeningByfid();

    List<ScreeningDTO> selectScreeningByFilmId(Integer filmId);
}