package com.woniu.woniuticket.cinema.dao;

import com.woniu.woniuticket.cinema.pojo.Ticket;

import java.util.List;

public interface TicketMapper {
    int deleteByPrimaryKey(Integer tid);

    int insert(Ticket record);

    int insertSelective(Ticket record);

    Ticket selectByPrimaryKey(Integer tid);

    int updateByPrimaryKeySelective(Ticket record);

    int updateByPrimaryKey(Ticket record);

    List<Ticket> selectTicketsByChipId(Integer ChipId);
}