package com.woniu.woniuticket.cinema.service.serviceimpl;

import com.woniu.woniuticket.cinema.dao.ScreeningMapper;
import com.woniu.woniuticket.cinema.execption.ScreeningExecption;
import com.woniu.woniuticket.cinema.pojo.Screening;
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
}
