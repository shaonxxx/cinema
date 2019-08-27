package com.woniu.woniuticket.cinema.pojo;

import java.util.Date;

public class User {
    private Integer userId;

    private String username;

    private String password;

    private String mobile;

    private String nickname;

    private Date registTime;

    private Integer vipState;

    private Date vipActivetime;

    private String heading;

    private String inviteCode;

    private String registCode;

    private Integer userState;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Date getRegistTime() {
        return registTime;
    }

    public void setRegistTime(Date registTime) {
        this.registTime = registTime;
    }

    public Integer getVipState() {
        return vipState;
    }

    public void setVipState(Integer vipState) {
        this.vipState = vipState;
    }

    public Date getVipActivetime() {
        return vipActivetime;
    }

    public void setVipActivetime(Date vipActivetime) {
        this.vipActivetime = vipActivetime;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public String getRegistCode() {
        return registCode;
    }

    public void setRegistCode(String registCode) {
        this.registCode = registCode;
    }

    public Integer getUserState() {
        return userState;
    }

    public void setUserState(Integer userState) {
        this.userState = userState;
    }
}