package com.woniu.woniuticket.cinema.controller;

import com.github.pagehelper.PageInfo;
import com.woniu.woniuticket.cinema.pojo.User;
import com.woniu.woniuticket.cinema.pojo.condition.UserCondition;
import com.woniu.woniuticket.cinema.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/member")
public class MemberController {

    @Autowired
    MemberService memberService;

    @RequestMapping("/list.do")
    @ResponseBody
    public ModelAndView showPage(@RequestParam(value="currentPage",required = true,defaultValue = "1") Integer currentPage,
                                 UserCondition condition){
        //System.out.println("123");
//        System.out.println("start:"+condition.getStart());
//        System.out.println("end:"+condition.getEnd());
//        System.out.println("nikeName:"+condition.getNikeName());
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("/member-list");
        modelAndView.addObject("con",condition);
        List<User> users=memberService.selectPageCondition(currentPage,10,condition);
        PageInfo<User> pageInfo=new PageInfo<>(users);
        System.out.println("用户名："+pageInfo.getList().get(0).getUserName());
        System.out.println(pageInfo);
        modelAndView.addObject("page",pageInfo);
        return modelAndView;
    }

    @RequestMapping("/add")
    @ResponseBody
    public String add(){
        User user=new User();
        for (int i=200;i<=300;i++){
            user.setHeadimg("heading"+i);
            user.setInviteCode("00"+i);
            user.setMobile("13988888"+i);
            user.setNickname(""+i);
            user.setPassword(""+i);
            user.setRegistCode("00"+(i-1));
            user.setRegistTime(new Date());
            user.setUserName(""+i);
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

    @InitBinder
    public void convertTime(DataBinder binder){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class,new CustomDateEditor(simpleDateFormat,true));
    }
}
