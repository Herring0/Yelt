package com.herring.yelt.models;

import javax.persistence.*;

@Entity
@Table(name="user_review")
public class UserReview {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer uid;
    private String mid;
    private String type;
    private String date;
    private String text;

    public UserReview(Integer uid, String mid, String type, String date, String text) {
        this.uid = uid;
        this.mid = mid;
        this.type = type;
        this.date = date;
        this.text = text;
    }

    public UserReview() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
