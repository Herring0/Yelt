package com.herring.yelt.models;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "user_movie")
public class UserMovie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer uid;
    private String mid;

    private int rating;
    @Column(name = "date")
    private String voteTime;

    public UserMovie(Integer uid, String mid, int rating, String voteTime) {
        this.uid = uid;
        this.mid = mid;
        this.rating = rating;
        this.voteTime = voteTime;
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

    public String getVoteTime() {
        return voteTime;
    }

    public void setVoteTime(String voteTime) {
        this.voteTime = voteTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserMovie userMovie = (UserMovie) o;
        return Objects.equals(id, userMovie.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "UserMovie{" +
                "uid=" + uid +
                ", mid='" + mid + '\'' +
                ", rating=" + rating +
                ", voteTime='" + voteTime + '\'' +
                '}';
    }
}
