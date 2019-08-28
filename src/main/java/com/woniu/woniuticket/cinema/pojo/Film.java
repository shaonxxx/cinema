package com.woniu.woniuticket.cinema.pojo;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;
import java.util.List;

@Document(indexName = "filmIndex",type = "film",shards = 5,replicas = 1)
public class Film {

    @Id
    private Integer filmId;
    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    private String filmName;
    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    private String stars;
    @Field(type = FieldType.Keyword)
    private Integer duration;
    @Field(type = FieldType.Keyword)
    private String info;
    @Field(type = FieldType.Double)
    private Double grage;
    @Field(type = FieldType.Date)
    private Date releseDate;
    @Field(index = false,type = FieldType.Keyword)
    private Integer peopleNum;
    @Field(type = FieldType.Keyword)
    private String filmStatus;
    @Field(index = false,type = FieldType.Keyword)
    private String covers;
    @Field(type = FieldType.Keyword)
    private String language;
    @Field(index = false,type = FieldType.Keyword)
    private String otherStats;
    @Field(type = FieldType.Keyword)
    private String categoryId;

    private List<Category> categories;

    private String categoryString;

    private String local;

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getCategoryString() {
        return categoryString;
    }

    public void setCategoryString(String categoryString) {
        this.categoryString = categoryString;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public Integer getFilmId() {
        return filmId;
    }

    public void setFilmId(Integer filmId) {
        this.filmId = filmId;
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

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Double getGrage() {
        return grage;
    }

    public void setGrage(Double grage) {
        this.grage = grage;
    }

    public Date getReleseDate() {
        return releseDate;
    }

    public void setReleseDate(Date releseDate) {
        this.releseDate = releseDate;
    }

    public Integer getPeopleNum() {
        return peopleNum;
    }

    public void setPeopleNum(Integer peopleNum) {
        this.peopleNum = peopleNum;
    }

    public String getFilmStatus() {
        return filmStatus;
    }

    public void setFilmStatus(String filmStatus) {
        this.filmStatus = filmStatus;
    }

    public String getCovers() {
        return covers;
    }

    public void setCovers(String covers) {
        this.covers = covers;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getOtherStats() {
        return otherStats;
    }

    public void setOtherStats(String otherStats) {
        this.otherStats = otherStats;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "Film{" +
                "filmId=" + filmId +
                ", filmName='" + filmName + '\'' +
                ", stars='" + stars + '\'' +
                ", duration=" + duration +
                ", info='" + info + '\'' +
                ", grage=" + grage +
                ", releseDate=" + releseDate +
                ", peopleNum=" + peopleNum +
                ", filmStatus='" + filmStatus + '\'' +
                ", covers='" + covers + '\'' +
                ", language='" + language + '\'' +
                ", otherStats='" + otherStats + '\'' +
                ", categoryId='" + categoryId + '\'' +
                '}';
    }
}