package com.woniu.woniuticket.cinema.service.serviceimpl;

import com.woniu.woniuticket.cinema.dao.HallMapper;
import com.woniu.woniuticket.cinema.pojo.Hall;
import com.woniu.woniuticket.cinema.pojo.Order;
import com.woniu.woniuticket.cinema.pojo.Result;
import com.woniu.woniuticket.cinema.pojo.Screening;
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

    @Override
    public int addHall(Hall hall) {
        hallMapper.insertSelective(hall);
        return 0;
    }

    // 根据排片Id关闭或打开放映厅状态
    @Override
    public Result updateHallStateByChipId(Integer chipId, String state) {
        Result result = null;
        // 通过排片id查询出排片对象
        Screening screening = hallMapper.selectScreeningByChipId(chipId);
        // 开启影厅
        if (state.equals("1")){
            if (screening == null){
                result.setCode("500");
                result.setMessage("排片信息有误,请重新检查!");
                result.setData(null);
                return result;
            }else {
                // 通过影厅Id开启影厅
                Integer hallState = Integer.parseInt(state);
                int row = hallMapper.updateHallStateByHallId(screening.getHallId(),hallState);
                if (row > 0){
                    result.setCode("200");
                    result.setMessage("影厅开启成功!");
                    result.setData(null);
                    return result;
                }else {
                    result.setCode("500");
                    result.setMessage("影厅开启失败!");
                    result.setData(null);
                    return result;
                }
            }
        }else {
            // 关闭影厅
            // 根据排片id查询是否有订单
            List<Order> orders = hallMapper.selectOrderByChipId(chipId);
            if (orders != null){
                result.setCode("500");
                result.setMessage("该影厅有已售订单未消费,不能关闭!");
                result.setData(null);
                return result;
            }else {
                // 关闭影厅状态(0:关闭,1:开启)
                Integer hallState = Integer.parseInt(state);
                int row = hallMapper.updateHallStateByHallId(screening.getHallId(),hallState);
                if (row > 0){
                    result.setCode("200");
                    result.setMessage("影厅开启成功!");
                    result.setData(null);
                    return result;
                }else {
                    result.setCode("500");
                    result.setMessage("影厅开启失败!");
                    result.setData(null);
                    return result;
                }
            }
        }
    }
}
