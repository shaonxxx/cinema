package com.woniu.woniuticket.cinema.service.serviceimpl;

import com.woniu.woniuticket.cinema.dao.*;
import com.woniu.woniuticket.cinema.dto.ScreeningDTO;
import com.woniu.woniuticket.cinema.execption.ScreeningExecption;
import com.woniu.woniuticket.cinema.pojo.*;
import com.woniu.woniuticket.cinema.service.ScreeningService;
import com.woniu.woniuticket.cinema.vo.ScreeningVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

    @Autowired
    CinemaMapper cinemaMapper;

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
    public List<ScreeningDTO> findScreeningByFilmId(Integer filmId) {
        return screeningMapper.selectScreeningByFilmId(filmId);
    }

    // 删除排片
    @Override
    public Result deleteScreeningByChipId(Integer chipId) {
        Result result = null;
        // 通过排片id查询订单对象
        List<Order> orders = screeningMapper.selectOrderByChipId(chipId);
        if (orders != null){
            result.setCode("500");
            result.setMessage("该影片有订单未消费,不能删除!");
            result.setData(null);
            return result;
        }else {
            // 删除排片(0:删除,1:存活)
            int row = screeningMapper.deleteScreeningByChipId(chipId);
            if (row > 0){
                result.setCode("200");
                result.setMessage("排片删除成功!");
                result.setData(null);
                return result;
            }else {
                result.setCode("500");
                result.setMessage("排片删除失败!");
                result.setData(null);
                return result;
            }
        }
    }

    @Override
    public Screening findScreenBychipid(Integer chipid) {
        return screeningMapper.selectByPrimaryKey(chipid);
    }

    @Override
    public List<Screening> findScreeningByhid(Integer hid) {

        return  screeningMapper.selectScreeningByfid();
    }


    @Override
    public List<ScreeningDTO> findScreeningByCondition(ScreeningVO screeningVO, Integer currentPage, Integer pagesize) {
        if(screeningVO.getFilmName()!=null && !screeningVO.getFilmName().equals("")){
            Film film = filmMapper.selectFilmByName(screeningVO.getFilmName());
            screeningVO.setFilmId(film.getFilmId());
        }
        List<ScreeningDTO> screeningDTOS = new ArrayList<>();
        List<Screening> screenings = screeningMapper.selectScreeningByCondition(screeningVO, currentPage, pagesize);
        for (Screening screening : screenings) {
                //查找电影信息
            Film film = filmMapper.selectByPrimaryKey(screening.getFilmId());
            //查找放映厅信息
            Hall hall = hallMapper.selectByPrimaryKey(screening.getHallId());
            //查找影院(放映点)信息
            Cinema cinema = cinemaMapper.selectByPrimaryKey(screening.getCinemaId());
            //封装ScreeningDTO对象
            ScreeningDTO screeningDTO = new ScreeningDTO();
                screeningDTO.setChipId(screening.getChipId());
                screeningDTO.setCinema(cinema);
                screeningDTO.setFilm(film);
                screeningDTO.setHall(hall);
                screeningDTO.setEndTime(screening.getEndTime());
                screeningDTO.setStartTime(screening.getStartTime());
                screeningDTO.setPrice(screening.getPrice());
            //将封装好的ScreeningDTO添加到集合中
            screeningDTOS.add(screeningDTO);
        }
        return screeningDTOS ;
    }

    /**
     * 添加排片信息
     * @param screening
     * @return
     */
    @Override
    public int addScreening(Screening screening) {
        //校验排片时间是否合理
        boolean bl = this.checkTime(screening);
        if(bl){
            throw new ScreeningExecption("时间安排不合理");
        }
        //校验放映厅当前时段是否已安排排片
        boolean flag = this.checkHall(screening);
        if(flag){
            throw new ScreeningExecption("当前时段已有排片");
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

    /**
     * 更新指定排片信息
     * @param screening
     * @return
     */
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

        //校验排片时间是否合理
        boolean bl = this.checkTime(screening);
        if(bl){
            throw new ScreeningExecption("时间安排不合理");
        }
        //校验放映厅当前时段是否已安排排片
        boolean flag = this.checkHall(screening);
        if(flag){
            throw new ScreeningExecption("当前时段已有排片");
        }else{
            screeningMapper.updateByPrimaryKeySelective(screening);
            return 200;
        }

    }


    /**
     * 校验当前放映厅当前时段是否已有排片
     * @param screening
     * @return
     */
    public boolean checkHall(Screening screening){
        long startTime = screening.getStartTime().getTime();
        long endTime = screening.getEndTime().getTime();
        //校验放映厅当前时段是否已安排排片
        boolean flag = false;//默认没有排片
        List<Screening> screenings = screeningMapper.selectScreeningsByHallId(screening.getHallId());
        for (Screening s : screenings) {
            if(endTime>s.getStartTime().getTime() || startTime<s.getEndTime().getTime()){
                flag = true;
            }
        }
       return flag;
    }

    /**
     * 校验排片时间是否安排合理
     * @param screening
     * @return
     */
    public boolean checkTime(Screening screening){
        boolean flag = false;
        long startTime = screening.getStartTime().getTime();
        long endTime = screening.getEndTime().getTime();
        /**
         * 开始时间在结束时间之后 返回false
         */
        if(startTime>endTime){
            flag = true;
        }

        return flag;
    }

}
