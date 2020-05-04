package com.herring.yelt.gson.models.movies;

import java.util.List;

public class MovieSimilarMovies {
    public int page;
    public List<Result> results;

    public class Result {
        public int id;
        public boolean video;
        public int vote_count;
        public float vote_average;
        public String title;
        public String release_date;
        public String original_language;
        public String main_genre;
        public int[] genre_ids;
        public String backdrop_path;
        public boolean adult;
        public String overview;
        public String poster_path;
        public float popularity;
    }
}
