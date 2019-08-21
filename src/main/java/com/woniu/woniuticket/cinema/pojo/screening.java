package com.woniu.woniuticket.cinema.pojo;

import java.util.Date;

public class screening {
    private Integer chipId;

    private Date startTime;

    private Date endTime;

    private Integer filmId;

    private Integer cinemaId;

    private Integer hallId;

    private Long price;

    public Integer getChipId() {
        return chipId;
    }

    public void setChipId(Integer chipId) {
        this.chipId = chipId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getFilmId() {
        return filmId;
    }

    public void setFilmId(Integer filmId) {
        this.filmId = filmId;
    }

    public Integer getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(Integer cinemaId) {
        this.cinemaId = cinemaId;
    }

    public Integer getHallId() {
        return hallId;
    }

    public void setHallId(Integer hallId) {
        this.hallId = hallId;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
}