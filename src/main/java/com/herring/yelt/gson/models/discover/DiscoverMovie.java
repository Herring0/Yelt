package com.herring.yelt.gson.models.discover;

import java.util.List;

public class DiscoverMovie {
    public int total_results;
    public List<Result> results;

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
