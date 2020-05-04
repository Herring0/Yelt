package com.herring.yelt.models;

import javax.persistence.*;

@Entity
@Table(name="movies")
public class Movie {

    @Id
    private String id;

    private Float rating;
    private Integer votes;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public Integer getVotes() {
        return votes;
    }

    public void setVotes(Integer votes) {
        this.votes = votes;
    }
}
