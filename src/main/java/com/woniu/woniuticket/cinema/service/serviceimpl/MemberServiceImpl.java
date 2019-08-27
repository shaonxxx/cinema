package com.woniu.woniuticket.cinema.service.serviceimpl;

import com.woniu.woniuticket.cinema.dao.UserMapper;
import com.woniu.woniuticket.cinema.pojo.User;
import com.woniu.woniuticket.cinema.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    UserMapper userMapper;

    @Override
    public List<User> selectPageCondition(Integer currentPage, Integer pageSize, User user) {
        return userMapper.selectPageCondition(currentPage,pageSize,user);
    }

    @Override
    public void changeActive(Integer id, Integer state) {
        userMapper.updateActive(id,state);
    }

    @Override
    public void delete(Integer id) {
        userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void updateInfo(User user) {
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public void add(User user) {
        userMapper.insertSelective(user);
    }
}
