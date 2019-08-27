package com.woniu.woniuticket.cinema.controller;

import com.github.pagehelper.PageInfo;
import com.woniu.woniuticket.cinema.pojo.User;
import com.woniu.woniuticket.cinema.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/member")
public class MemberController {

    @Autowired
    MemberService memberService;

    @RequestMapping("/list.do")
    @ResponseBody
    public ModelAndView showPage(@RequestParam(value="index",required = true,defaultValue = "1") Integer index,
                                 User user){
        //System.out.println("123");
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("/member-list");
        List<User> users=memberService.selectPageCondition(index,10,user);
        PageInfo<User> pageInfo=new PageInfo<>(users);
        System.out.println(pageInfo);
        modelAndView.addObject("page",pageInfo);
        return modelAndView;
    }

    @RequestMapping("/add")
    @ResponseBody
    public String add(){
        User user=new User();
        for (int i=200;i<=300;i++){
            user.setHeading("heading"+i);
            user.setInviteCode("00"+i);
            user.setMobile("13988888"+i);
            user.setNickname(""+i);
            user.setPassword(""+i);
            user.setRegistCode("00"+(i-1));
            user.setRegistTime(new Date());
            user.setUsername(""+i);
            user.setUserState(0);
            user.setVipActivetime(new Date());
            user.setVipState(0);
            memberService.add(user);
        }
        return "添加成功";
    }


    @RequestMapping("/changeState")
    @ResponseBody
    public String changeState(Integer id,Integer state){
        memberService.changeActive(id,state);
        if(state==0){
            return "恢复成功";
        }else{
            return "删除成功";
        }
    }


    @RequestMapping("/delete")
    @ResponseBody
    public String delete(Integer id){
        try{
            memberService.delete(id);
        }catch (Exception e){
            e.printStackTrace();
            return "出错误";
        }
        return "删除成功";
    }


    @RequestMapping("/update")
    @ResponseBody
    public String updateInfo(User user){
        try{
            memberService.updateInfo(user);
        }catch (Exception e){
            e.printStackTrace();
            return "更新失败";
        }
        return "更新成功";
    }
}
