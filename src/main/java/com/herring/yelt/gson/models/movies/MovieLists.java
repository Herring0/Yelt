package com.herring.yelt.gson.models.movies;

import java.util.List;

public class MovieLists {
    public int id;
    public int page;
    public List<Result> results;
    public int total_pages;
    public int total_results;

    public class Result {
        public String description;
        public int favorite_count;
        public int id;
        public int item_count;
        public String iso_639_1;
        public String list_type;
        public String name;
        public String poster_path;
    }
}
