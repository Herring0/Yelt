package com.herring.yelt.gson.models.movies;

import java.util.List;

public class MovieReleaseDates {
    public int id;
    public List<Results> results;

    public class Results {
        public String iso_3166_1;
        public List<ReleaseDates> release_dates;

        public class ReleaseDates {
            public String certification;
            public String iso_639_1;
            public String note;
            public String release_date;
            public int type;
        }
    }
}
