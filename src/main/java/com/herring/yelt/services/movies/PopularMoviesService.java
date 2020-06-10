package com.herring.yelt.services.movies;

import com.herring.yelt.utils.TMDbRequester;
import com.herring.yelt.gson.models.movies.MoviePopular;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PopularMoviesService {

    @Autowired
    private TMDbRequester tmDbRequester;

    public MoviePopular getPopularMovies() {
        return tmDbRequester.getPopularMovies();
    }
}
