package com.woniu.woniuticket.cinema.service;

import com.woniu.woniuticket.cinema.pojo.User;

import java.util.List;

public interface MemberService {

    /**
     * 条件查询
     * @param currentPage
     * @param pageSize
     * @param user
     * @return
     */
    public List<User> selectPageCondition(Integer currentPage,Integer pageSize,User user);


    public void changeActive(Integer id,Integer state);

    public void delete(Integer id);

    public void updateInfo(User user);

    public void add(User user);



}
