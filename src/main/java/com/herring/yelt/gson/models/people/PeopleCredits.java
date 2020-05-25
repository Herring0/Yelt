package com.herring.yelt.gson.models.people;

import java.util.List;

public class PeopleCredits {
    public List<Cast> cast;
    public List<Crew> crew;
    public int id;



    public class Cast implements Comparable<PeopleCredits.Cast> {
        public String character;
        public String credit_id;
        public String release_date;
        public String title;
        public int[] genre_ids;
        public String original_language;
        public String original_title;
        public int id;
        public String backdrop_path;
        public String overview;
        public String poster_path;

        @Override
        public int compareTo(PeopleCredits.Cast b) {
            if ((release_date.equals("—")) && (b.release_date.equals("—")))
                return 0;
            else if (release_date.equals("—"))
                return 1;
            else if (b.release_date.equals("—"))
                return -1;
            else
                return Integer.valueOf(release_date).compareTo(Integer.valueOf(b.release_date));
        }

        @Override
        public String toString() {
            return release_date;
        }
    }

    public class Crew implements Comparable<PeopleCredits.Crew> {
        public int id;
        public String department;
        public String original_language;
        public String original_title;
        public String job;
        public String overview;
        public String poster_path;
        public String backdrop_path;
        public String title;
        public int[] genre_ids;
        public String release_date;
        public String credit_id;

        @Override
        public int compareTo(PeopleCredits.Crew b) {
            if ((release_date.equals("—")) && (b.release_date.equals("—")))
                return 0;
            else if (release_date.equals("—"))
                return 1;
            else if (b.release_date.equals("—"))
                return -1;
            else
                return Integer.valueOf(release_date).compareTo(Integer.valueOf(b.release_date));
        }

        @Override
        public String toString() {
            return release_date;
        }
    }
}
