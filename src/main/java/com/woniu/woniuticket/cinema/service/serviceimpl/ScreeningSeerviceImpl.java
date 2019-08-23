package com.woniu.woniuticket.cinema.service.serviceimpl;

import com.woniu.woniuticket.cinema.dao.FilmMapper;
import com.woniu.woniuticket.cinema.dao.HallMapper;
import com.woniu.woniuticket.cinema.dao.ScreeningMapper;
import com.woniu.woniuticket.cinema.dao.TicketMapper;
import com.woniu.woniuticket.cinema.dto.ScreeningDTO;
import com.woniu.woniuticket.cinema.execption.ScreeningExecption;
import com.woniu.woniuticket.cinema.pojo.Film;
import com.woniu.woniuticket.cinema.pojo.Screening;
import com.woniu.woniuticket.cinema.pojo.Ticket;
import com.woniu.woniuticket.cinema.service.ScreeningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Service
public class ScreeningSeerviceImpl implements ScreeningService {

    @Autowired
    ScreeningMapper screeningMapper;

    @Autowired
    FilmMapper filmMapper;

    @Autowired
    TicketMapper ticketMapper;

    @Autowired
    HallMapper hallMapper;

    @Override
    public List<Screening> findScreeningByNowDate(Integer hid) {
        try {
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            List<Screening> screenings= screeningMapper.SelectByNewDate(hid,simpleDateFormat.format(new Date()));
            return screenings;
        }catch (Exception e){
            e.printStackTrace();
            throw new ScreeningExecption("获取排片信息失败");
        }

    }

    @Override
    public List<Screening> findScreeningByhid(Integer hid) {

        return  screeningMapper.selectScreeningByfid();
    }


    @Override
    public List<Screening> findScreeningByCondition(ScreeningDTO screeningDTO, Integer currentPage, Integer pagesize) {
        if(screeningDTO.getFilmName()!=null && !screeningDTO.getFilmName().equals("")){
            Film film = filmMapper.selectFilmByName(screeningDTO.getFilmName());
            screeningDTO.setFilmId(film.getFilmId());
        }

        return screeningMapper.selectScreeningByCondition(screeningDTO,currentPage,pagesize);
    }

    @Override
    public int addScreening(Screening screening) {
        long starTime = screening.getStartTime().getTime();
        long endTime = screening.getEndTime().getTime();
        /**
         * 校验时间是否合理
         */
        if(starTime>endTime){
            return 500;
        }
        //校验放映厅当前时段是否已安排排片
        boolean flag = false;//默认没有排片
        List<Screening> screenings = screeningMapper.selectScreeningsByHallId(screening.getHallId());
        for (Screening s : screenings) {
            if(endTime>s.getStartTime().getTime() || starTime<s.getEndTime().getTime()){
                flag = true;
            }
        }
        if(flag){
            return 500;
        }else{
            screeningMapper.insertSelective(screening);
            return 200;
        }
    }

    @Override
    public int removeScreenings(List<String> ids) {
        screeningMapper.deleteScreenings(ids);
        return 200;
    }

    @Override
    public int updateScreening(Screening screening) {
        /**
         * 校验原排片信息是否有电影票售出
         */
        Screening oldScreening = screeningMapper.selectByPrimaryKey(screening.getChipId());
        List<Ticket> tickets = ticketMapper.selectTicketsByChipId(screening.getChipId());
        for (Ticket ticket : tickets) {
            if(ticket.getHallName().equals(hallMapper.selectByPrimaryKey(screening.getHallId()).getHallName())){
                if(ticket.getStartTime().getTime() != oldScreening.getStartTime().getTime()){
                    throw new ScreeningExecption("电影票已售出，无法修改");
                }
            }
        }


        Long startTime = screening.getStartTime().getTime();
        Long endTime = screening.getEndTime().getTime();
        if(startTime>endTime){
            return 500;
        }



        return 200;
    }



}
