package com.herring.yelt.services.movies;

import com.herring.yelt.TMDbRequester;
import com.herring.yelt.gson.models.movies.MoviePopular;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PopularMoviesService {

    @Autowired
    private TMDbRequester tmDbRequester;

    public MoviePopular getPopularMovies() {
        return tmDbRequester.getPopularMovies();
    }
}
