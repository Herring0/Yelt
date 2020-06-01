package com.herring.yelt;

import com.google.gson.Gson;
import com.herring.yelt.gson.models.discover.DiscoverMovie;
import com.herring.yelt.gson.models.genres.GenresMovieList;
import com.herring.yelt.gson.models.movies.*;
import com.herring.yelt.gson.models.people.PeopleCredits;
import com.herring.yelt.gson.models.people.PeopleDetails;
import com.herring.yelt.gson.models.search.SearchMovies;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Component("tmdb_requester")
public class TMDbRequester {

    @Value("${api_key}")
    private String apiKey;

    private final String BASE_URL = "https://api.themoviedb.org/3";

    public MovieDetails getMovieDetails(String id) {
        MovieDetails movieDetails;

        StringBuilder urlBuilder = new StringBuilder();
        urlBuilder.append(BASE_URL);
        urlBuilder.append("/movie/");
        urlBuilder.append(id);
        urlBuilder.append("?api_key=");
        urlBuilder.append(apiKey);

        String result = new RestTemplate().getForObject(urlBuilder.toString(), String.class);

        movieDetails = new Gson().fromJson(result, MovieDetails.class);
        System.out.println("Movie details received");
        return movieDetails;
    }

    public PeopleDetails getPeopleDetails(String id) {
        PeopleDetails peopleDetails;

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(BASE_URL);
        stringBuilder.append("/person/");
        stringBuilder.append(id);
        stringBuilder.append("?api_key=");
        stringBuilder.append(apiKey);
        String result = new RestTemplate().getForObject(stringBuilder.toString(), String.class);

        peopleDetails = new Gson().fromJson(result, PeopleDetails.class);
        System.out.println("People details received");
        return peopleDetails;
    }

    public MovieCredits getMovieCredits(String id) {
        MovieCredits movieCredits;

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(BASE_URL);
        stringBuilder.append("/movie/");
        stringBuilder.append(id);
        stringBuilder.append("/credits");
        stringBuilder.append("?api_key=");
        stringBuilder.append(apiKey);
        String result = new RestTemplate().getForObject(stringBuilder.toString(), String.class);

        movieCredits = new Gson().fromJson(result, MovieCredits.class);
        System.out.println("Movie credits received");
        return movieCredits;
    }

    public MovieReleaseDates getMovieReleaseDates(String id) {
        MovieReleaseDates movieReleaseDates;

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(BASE_URL);
        stringBuilder.append("/movie/");
        stringBuilder.append(id);
        stringBuilder.append("/release_dates");
        stringBuilder.append("?api_key=");
        stringBuilder.append(apiKey);
        String result = new RestTemplate().getForObject(stringBuilder.toString(), String.class);

        movieReleaseDates = new Gson().fromJson(result, MovieReleaseDates.class);
        System.out.println("Movie release dates received");
        return movieReleaseDates;
    }

    public MovieSimilarMovies getSimilarMovies(String id) {
        MovieSimilarMovies movieSimilarMovies;

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(BASE_URL);
        stringBuilder.append("/movie/");
        stringBuilder.append(id);
        stringBuilder.append("/similar");
        stringBuilder.append("?api_key=");
        stringBuilder.append(apiKey);
        stringBuilder.append("&page=1");
        String result = new RestTemplate().getForObject(stringBuilder.toString(), String.class);

        movieSimilarMovies = new Gson().fromJson(result, MovieSimilarMovies.class);
        System.out.println("Similar movies received");
        return movieSimilarMovies;
    }

    public GenresMovieList getGenresMovieList() {
        GenresMovieList genresMovieList;

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(BASE_URL);
        stringBuilder.append("/genre/movie/list");
        stringBuilder.append("?api_key=");
        stringBuilder.append(apiKey);
        String result = new RestTemplate().getForObject(stringBuilder.toString(), String.class);

        genresMovieList = new Gson().fromJson(result, GenresMovieList.class);
        System.out.println("Movie genres received");
        return genresMovieList;
    }

    public MovieVideos getMovieVideos(String id) {
        MovieVideos movieVideos;

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(BASE_URL);
        stringBuilder.append("/movie/");
        stringBuilder.append(id);
        stringBuilder.append("/videos");
        stringBuilder.append("?api_key=");
        stringBuilder.append(apiKey);
        String result = new RestTemplate().getForObject(stringBuilder.toString(), String.class);

        movieVideos = new Gson().fromJson(result, MovieVideos.class);
        System.out.println("Movie videos received");
        return movieVideos;
    }

    public MovieLists getMovieLists(String id) {
        MovieLists movieLists;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(BASE_URL);
        stringBuilder.append("/movie/");
        stringBuilder.append(id);
        stringBuilder.append("/lists");
        stringBuilder.append("?api_key=");
        stringBuilder.append(apiKey);
        String result = new RestTemplate().getForObject(stringBuilder.toString(), String.class);

        movieLists = new Gson().fromJson(result, MovieLists.class);
        System.out.println("Movie lists received");
        return movieLists;
    }

    public PeopleCredits getPeopleCredits(String id) {
        PeopleCredits peopleCredits;

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(BASE_URL);
        stringBuilder.append("/person/");
        stringBuilder.append(id);
        stringBuilder.append("/movie_credits");
        stringBuilder.append("?api_key=");
        stringBuilder.append(apiKey);
        String result = new RestTemplate().getForObject(stringBuilder.toString(), String.class);

        peopleCredits = new Gson().fromJson(result, PeopleCredits.class);
        System.out.println("People credits received");
        return peopleCredits;
    }

    public DiscoverMovie getDiscoverMovies(String pId) {
        DiscoverMovie discoverMovies;

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(BASE_URL);
        stringBuilder.append("/discover");
        stringBuilder.append("/movie");
        stringBuilder.append("?api_key=");
        stringBuilder.append(apiKey);
        stringBuilder.append("&language=en-US");
        stringBuilder.append("&sort_by=popularity.desc");
        stringBuilder.append("&include_adult=true");
        stringBuilder.append("&include_video=false");
        stringBuilder.append("&page=1");
        stringBuilder.append("&with_people=");
        stringBuilder.append(pId);

        String result = new RestTemplate().getForObject(stringBuilder.toString(), String.class);

        discoverMovies = new Gson().fromJson(result, DiscoverMovie.class);
        System.out.println("Discover movies received");
        return discoverMovies;
    }

    public MoviePopular getPopularMovies() {
        MoviePopular moviePopular;

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(BASE_URL);
        stringBuilder.append("/movie");
        stringBuilder.append("/popular");
        stringBuilder.append("?api_key=");
        stringBuilder.append(apiKey);

        String result = new RestTemplate().getForObject(stringBuilder.toString(), String.class);

        moviePopular = new Gson().fromJson(result, MoviePopular.class);
        System.out.println("Popular movies received");
        return moviePopular;
    }
}
