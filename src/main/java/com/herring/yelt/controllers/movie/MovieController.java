package com.herring.yelt.controllers.movie;

import com.herring.yelt.TMDbRequester;
import com.herring.yelt.gson.models.genres.GenresMovieList;
import com.herring.yelt.gson.models.movies.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private TMDbRequester tmDbRequester;

    @GetMapping("/{id}")
    public String main(@PathVariable(value = "id") String id, Model model) {
        MovieDetails movieDetails = tmDbRequester.getMovieDetails(id);
        MovieCredits movieCredits = tmDbRequester.getMovieCredits(id);
        MovieReleaseDates movieReleaseDates = tmDbRequester.getMovieReleaseDates(id);
        MovieSimilarMovies similarMovies = tmDbRequester.getSimilarMovies(id);
        GenresMovieList genresMovieList = tmDbRequester.getGenresMovieList();
        MovieVideos movieVideos = tmDbRequester.getMovieVideos(id);

        movieDetails.release_date = movieDetails.release_date.split("-")[0];

        List<MovieCredits.Crew> directors = new ArrayList<>();
        for (MovieCredits.Crew crew : movieCredits.crew) {
            if (crew.department.equals("Directing")) {
                directors.add(crew);
            }
        }

        List<MovieCredits.Crew> writers = new ArrayList<>();

        for (MovieCredits.Crew crew : movieCredits.crew) {
            OUTER_LOOP:
            if (crew.department.equals("Writing")) {
                for (MovieCredits.Crew writer : writers) {
                    if (writer.name.equals(crew.name))
                        break OUTER_LOOP;
                }
                writers.add(crew);
            }
        }

        List<MovieCredits.Crew> producers = new ArrayList<>();
        for (MovieCredits.Crew crew : movieCredits.crew) {
            OUTER_LOOP:
            if (crew.department.equals("Production")) {
                for (MovieCredits.Crew producer : producers) {
                    if (producer.name.equals(crew.name))
                        break OUTER_LOOP;
                }
                producers.add(crew);
            }
        }

        List<MovieCredits.Crew> cameras = new ArrayList<>();
        for (MovieCredits.Crew crew : movieCredits.crew) {
            OUTER_LOOP:
            if (crew.department.equals("Camera")) {
                for (MovieCredits.Crew camera : cameras) {
                    if (camera.name.equals(crew.name))
                        break OUTER_LOOP;
                }
                cameras.add(crew);
            }
        }

        for (MovieSimilarMovies.Result result : similarMovies.results) {
            for (GenresMovieList.Genre genre : genresMovieList.genres) {
                if (result.genre_ids[0] == genre.id) {
                    result.main_genre = genre.name;
                }
            }
        }

        String certification = "";
        for (MovieReleaseDates.Results result : movieReleaseDates.results) {
            if (result.iso_3166_1.equals("US")) {
                certification = result.release_dates.get(0).certification;
                break;
            }
        }

        movieDetails.revenue_in_dollars = NumberFormat.getCurrencyInstance(Locale.US).format(movieDetails.revenue);

        model.addAttribute("movie", movieDetails);
        model.addAttribute("credits", movieCredits);
        model.addAttribute("directors", directors);
        model.addAttribute("writers", writers);
        model.addAttribute("producers", producers);
        model.addAttribute("cameras", cameras);
        model.addAttribute("certification", certification);
        model.addAttribute("similarMovies", similarMovies);
        model.addAttribute("movieVideos", movieVideos);
        return "movie/Movie";
    }
}
