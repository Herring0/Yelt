package com.herring.yelt.gson.models.movies;

import java.util.List;
import java.util.Objects;

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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Crew crew = (Crew) o;
            return id == crew.id;
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }
    }
}
