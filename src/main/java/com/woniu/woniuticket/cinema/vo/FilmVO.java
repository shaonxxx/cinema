package com.woniu.woniuticket.cinema.vo;

import java.util.Date;

public class FilmVO {

    private String filmName;

    private String stars;

    private Integer categoryId;

    private String filmStatus;

    private Date releseDate;

    public Date getReleseDate() {
        return releseDate;
    }

    public void setReleseDate(Date releseDate) {
        this.releseDate = releseDate;
    }

    public String getFilmName() {
        return filmName;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    public String getStars() {
        return stars;
    }

    public void setStars(String stars) {
        this.stars = stars;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getFilmStatus() {
        return filmStatus;
    }

    public void setFilmStatus(String filmStatus) {
        this.filmStatus = filmStatus;
    }
}
