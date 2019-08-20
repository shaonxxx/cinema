package com.woniu.woniuticket.cinema.pojo;

import java.util.Date;

public class CinemaComment {
    private Integer cinemaCommentId;

    private Integer cinemaId;

    private String content;

    private Date commentTime;

    private String nickName;

    private Integer commentStatus;

    public Integer getCinemaCommentId() {
        return cinemaCommentId;
    }

    public void setCinemaCommentId(Integer cinemaCommentId) {
        this.cinemaCommentId = cinemaCommentId;
    }

    public Integer getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(Integer cinemaId) {
        this.cinemaId = cinemaId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getCommentStatus() {
        return commentStatus;
    }

    public void setCommentStatus(Integer commentStatus) {
        this.commentStatus = commentStatus;
    }
}