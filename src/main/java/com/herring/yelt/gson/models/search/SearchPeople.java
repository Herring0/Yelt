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
        public List<KnownFor> knownFor;
        public String id;
        public String name;
        public float popularity;
        public String department;
        public String biography;

        public class KnownFor {
            public String poster_path;
            public String overview;
            public String media_type;
            public String backdrop_path;
            public String title;
            public String id;
        }
    }
}
