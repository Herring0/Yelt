package com.herring.yelt.services.movies;

import com.herring.yelt.TMDbRequester;
import com.herring.yelt.gson.models.movies.MovieTopRated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopRatedMovieService {

    @Autowired
    private TMDbRequester tmDbRequester;

    public MovieTopRated getTopRatedMovies() {
        return tmDbRequester.getTopRatedMovies();
    }
}
