package com.woniu.woniuticket.cinema.service.serviceimpl;

import com.woniu.woniuticket.cinema.dao.HallMapper;
import com.woniu.woniuticket.cinema.pojo.Hall;
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

    //  更新影厅开启关闭状态
    @Override
    public Result updateHallStateByChipId(Integer hallId, String state) {
        Result result = null;
        // 通过影厅Id查询出排片对象
        Screening screening = hallMapper.selectScreeningByChipId(hallId);
        // 开启影厅
        if (state.equals("1")){
            // 通过影厅Id开启影厅
            Integer hallState = Integer.parseInt(state);
            int row = hallMapper.updateHallStateByHallId(hallId,hallState);
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
        }else {
            // 关闭影厅
            if (screening != null){
                result.setCode("500");
                result.setMessage("该影厅有排片信息,不能关闭!");
                result.setData(null);
                return result;
            }else {
                // 通过影厅Id关闭影厅
                Integer hallState = Integer.parseInt(state);
                int row = hallMapper.updateHallStateByHallId(hallId,hallState);
                if (row > 0){
                    result.setCode("200");
                    result.setMessage("影厅关闭成功!");
                    result.setData(null);
                    return result;
                }else {
                    result.setCode("500");
                    result.setMessage("影厅关闭失败!");
                    result.setData(null);
                    return result;
                }
            }
        }
    }
}
