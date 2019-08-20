package com.woniu.woniuticket.cinema.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class Cinema {
    private Integer cinemaId;

    private String location;

    private String logo;

    private String ciname;

    private Date joinTime;

    private String relation;

    private String telphone;

    private String describe;

    private Integer state;

    private BigDecimal lgt;

    private BigDecimal dim;

    public Integer getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(Integer cinemaId) {
        this.cinemaId = cinemaId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getCiname() {
        return ciname;
    }

    public void setCiname(String ciname) {
        this.ciname = ciname;
    }

    public Date getJoinTime() {
        return joinTime;
    }

    public void setJoinTime(Date joinTime) {
        this.joinTime = joinTime;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public BigDecimal getLgt() {
        return lgt;
    }

    public void setLgt(BigDecimal lgt) {
        this.lgt = lgt;
    }

    public BigDecimal getDim() {
        return dim;
    }

    public void setDim(BigDecimal dim) {
        this.dim = dim;
    }
}