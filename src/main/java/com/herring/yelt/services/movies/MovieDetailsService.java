package com.herring.yelt.services.movies;

import com.herring.yelt.TMDbRequester;
import com.herring.yelt.gson.models.movies.MovieDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieDetailsService {
    @Autowired
    private TMDbRequester tmDbRequester;

    public List<MovieDetails> getMoviesById(List<String> ids) {
        List<MovieDetails> movieDetails = new ArrayList<>();
        for (String id :
                ids) {
            Thread thread = new Thread(() -> {
                movieDetails.add(tmDbRequester.getMovieDetails(id));
            });
            try {
                thread.start();
                thread.join();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return movieDetails;
    }
}
