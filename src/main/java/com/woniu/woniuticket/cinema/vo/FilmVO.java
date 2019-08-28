package com.woniu.woniuticket.cinema.vo;

import java.util.Date;

public class FilmVO {

    private String filmName;

    private String stars;

    private String categoryId;

    private String filmStatus;

    private Date releseDate;

    private String local;

    private String language;

    public Date getReleseDate() {
        return releseDate;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
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

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getFilmStatus() {
        return filmStatus;
    }

    public void setFilmStatus(String filmStatus) {
        this.filmStatus = filmStatus;
    }
}
