package com.herring.yelt.gson.models.movies;

import java.util.List;

public class MovieCredits {
    public int id;
    public List<Cast> cast;
    public List<Crew> crew;

    public class Cast {
        public int cast_id;
        public String character;
        public String credit_id;
        public int gender;
        public int id;
        public String name;
        public int order;
        public String profile_path;
    }

    public class Crew {
        public String credit_id;
        public String department;
        public int gender;
        public int id;
        public String job;
        public String name;
        public String profile_path;
    }
}
