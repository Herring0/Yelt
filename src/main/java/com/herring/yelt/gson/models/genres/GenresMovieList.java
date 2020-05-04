package com.herring.yelt.gson.models.genres;

import java.util.List;

public class GenresMovieList {
    public List<Genre> genres;

    public class Genre {
        public int id;
        public String name;
    }
}
