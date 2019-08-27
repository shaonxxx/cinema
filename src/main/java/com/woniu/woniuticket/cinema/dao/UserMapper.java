package com.woniu.woniuticket.cinema.dao;

import com.woniu.woniuticket.cinema.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    List<User> selectPageCondition(@Param("currentPage") Integer currentPage,@Param("pageSize") Integer pageSize,@Param("user") User user);

    public int updateActive(@Param("id") Integer id,@Param("state") Integer state);

}