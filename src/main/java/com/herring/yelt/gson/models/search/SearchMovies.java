package com.herring.yelt.gson.models.search;

import java.util.List;

public class SearchMovies {
    public int page;
    public List<Result> results;
    public int total_results;
    public int total_pages;

    public class Result {
        public String poster_path;
        public String overview;
        public String release_date;
        public List<Integer> genre_ids;
        public int id;
        public String title;
        public String backdrop_path;
    }
}
