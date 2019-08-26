package com.woniu.woniuticket.cinema.controller.user;

import com.woniu.woniuticket.cinema.realm.UserRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {

    private UserRealm userRealm=new UserRealm();
    @RequestMapping("/login")
    public String tesstLogin(String username,String password,HttpServletRequest request){
        try{
            Subject subject=SecurityUtils.getSubject();
            UsernamePasswordToken token=new UsernamePasswordToken(username,password);
            subject.login(token);
            Session session = subject.getSession();
            session.setAttribute("username",username);
            session.setAttribute("password",password);
        }catch (Exception e){
            return "login";
        }
        return "index";
    }
}
