package com.herring.yelt.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user_movie")
public class UserMovie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer uid;
    private String mid;

    private int rating;
    private String vote_time;

    public UserMovie(Integer uid, String mid, int rating, String vote_time) {
        this.uid = uid;
        this.mid = mid;
        this.rating = rating;
        this.vote_time = vote_time;
    }

    public UserMovie() {
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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getVote_time() {
        return vote_time;
    }

    public void setVote_time(String vote_time) {
        this.vote_time = vote_time;
    }
}
