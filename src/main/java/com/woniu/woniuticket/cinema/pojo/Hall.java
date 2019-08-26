package com.woniu.woniuticket.cinema.pojo;

public class Hall {


    private Integer hallId;

    private String hallName;

    private Integer seatNum;

    private Integer cinemaId;

    private String seats;

    private Integer state;

    private String seatmap;

    public Integer getHallId() {
        return hallId;
    }

    public void setHallId(Integer hallId) {
        this.hallId = hallId;
    }

    public String getHallName() {
        return hallName;
    }

    public void setHallName(String hallName) {
        this.hallName = hallName;
    }

    public Integer getSeatNum() {
        return seatNum;
    }

    public void setSeatNum(Integer seatNum) {
        this.seatNum = seatNum;
    }

    public Integer getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(Integer cinemaId) {
        this.cinemaId = cinemaId;
    }

    public String getSeats() {
        return seats;
    }

    public void setSeats(String seats) {
        this.seats = seats;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getSeatmap() {
        return seatmap;
    }

    public void setSeatmap(String seatmap) {
        this.seatmap = seatmap;
    }

    @Override
    public String toString() {
        return "Hall{" +
                "hallId=" + hallId +
                ", hallName='" + hallName + '\'' +
                ", seatNum=" + seatNum +
                ", cinemaId=" + cinemaId +
                ", seats='" + seats + '\'' +
                ", state=" + state +
                ", seatmap='" + seatmap + '\'' +
                '}';
    }
}