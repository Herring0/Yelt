package com.herring.yelt.gson.models.search;

import java.util.List;

public class SearchPeople {

    public int page;
    public List<Result> results;
    public int total_results;
    public int total_pages;


    public class Result {
        public String dataType;
        public String profile_path;
        public int id;
        public String name;
        public float popularity;
    }
}
