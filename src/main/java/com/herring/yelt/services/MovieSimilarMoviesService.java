package com.herring.yelt.services;

import com.herring.yelt.TMDbRequester;
import com.herring.yelt.gson.models.genres.GenresMovieList;
import com.herring.yelt.gson.models.movies.MovieCredits;
import com.herring.yelt.gson.models.movies.MovieSimilarMovies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieSimilarMoviesService {
    @Autowired
    private TMDbRequester tmDbRequester;

    private GenresMovieList genresMovieList;

    public MovieSimilarMovies getSimilarMovies(MovieSimilarMovies similarMovies) {
        genresMovieList = tmDbRequester.getGenresMovieList();
        for (MovieSimilarMovies.Result result : similarMovies.results) {
            for (GenresMovieList.Genre genre : genresMovieList.genres) {
                if (result.genre_ids.length > 0) {
                    if (result.genre_ids[0] == genre.id) {
                        result.main_genre = genre.name;
                    }
                }
            }
        }
        return similarMovies;
    }
}
