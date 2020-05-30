package com.herring.yelt.gson.models.movies;

import java.util.List;

public class MovieDetails {
    public boolean adult;
    public String backdrop_path;
    public Object belongs_to_collection;
    public int budget;
    public List<Genres> genres;
    public String homepage;
    public int id;
    public String imdb_id;
    public String original_language;
    public String original_title;
    public String overview;
    public float popularity;
    public String poster_path;
    public List<ProdCompanies> production_companies;
    public List<ProdCountries> production_countries;
    public String release_date;
    public long revenue;
    public String revenue_in_dollars;
    public int runtime;
    public List<SpokenLanguages> spoken_languages;
    public String status;
    public String tagline;
    public String title;
    public boolean video;

    private class Genres {
        public int id;
        public String name;
    }

    private class ProdCompanies {
        public int id;
        public String logo_path;
        public String name;
        public String origin_country;
    }

    private class ProdCountries {
        public String name;
    }

    private class SpokenLanguages {
        public String name;
    }

    @Override
    public String toString() {
        return title;
    }
}
