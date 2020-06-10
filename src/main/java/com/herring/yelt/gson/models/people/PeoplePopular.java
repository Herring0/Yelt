package com.herring.yelt.gson.models.people;


import java.util.List;

public class PeoplePopular {

    public int page;
    public List<Result> results;
    public int total_results;
    public int total_pages;

    public class Result {
        public String profile_path;
        public String id;
        public String name;
        public float popularity;
    }
}
