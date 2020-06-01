package com.herring.yelt.gson.models.movies;

import java.util.List;

public class MoviePopular {
    public int page;
    public List<Result> results;
    public int total_results;
    public int total_pages;

    public class Result {
        public String poster_path;
        public String overview;
        public String release_date;
        public String id;
        public String title;
        public String backdrop_path;
        public float popularity;
    }
}
