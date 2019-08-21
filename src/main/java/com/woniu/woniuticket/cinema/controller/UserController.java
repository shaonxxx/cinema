package com.woniu.woniuticket.cinema.controller;

import com.woniu.woniuticket.cinema.dao.UserMapper;
import com.woniu.woniuticket.cinema.pojo.Result;
import com.woniu.woniuticket.cinema.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class UserController {

    @Autowired
    UserMapper userMapper;

    @RequestMapping("/login")
    public Result login(String username, String password){
        User user=userMapper.selectByUsername(username);
        System.out.println(user);
        if(user!=null){
            return new Result<User>("0","登录成功",user);
        }
        return new Result("404","用户为找到",null);
    }
}
