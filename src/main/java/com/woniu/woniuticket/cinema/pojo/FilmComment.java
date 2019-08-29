package com.woniu.woniuticket.cinema.pojo;

import java.util.Date;

public class FilmComment {
    private Integer filmCommentId;

    private Integer filmId;

    private String content;

    private Date commentTime;

    private String nickName;

    private Integer commentStatus;

    private Integer score;

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getFilmCommentId() {
        return filmCommentId;
    }

    public void setFilmCommentId(Integer filmCommentId) {
        this.filmCommentId = filmCommentId;
    }

    public Integer getFilmId() {
        return filmId;
    }

    public void setFilmId(Integer filmId) {
        this.filmId = filmId;
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