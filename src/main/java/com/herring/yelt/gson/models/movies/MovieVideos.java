package com.herring.yelt.gson.models.movies;

import java.util.List;

public class MovieVideos {
    public int id;
    public List<Result> results;

    public class Result {
        public String id;
        public String iso_639_1;
        public String iso_3166_1;
        public String key;
        public String name;
        public String site;
        public int size;
        public String type;
    }
}
