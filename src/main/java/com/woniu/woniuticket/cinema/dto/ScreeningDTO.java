package com.woniu.woniuticket.cinema.dto;

import com.woniu.woniuticket.cinema.pojo.Cinema;
import com.woniu.woniuticket.cinema.pojo.Film;
import com.woniu.woniuticket.cinema.pojo.Hall;

import java.util.Date;

public class ScreeningDTO {

    private Integer chipId;

    private Date startTime;

    private Date endTime;

    private Film film;

    private Hall hall;

    private Cinema cinema;

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

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }

    public Cinema getCinema() {
        return cinema;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
}
