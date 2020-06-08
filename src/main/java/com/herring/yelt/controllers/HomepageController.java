package com.herring.yelt.controllers;

import com.herring.yelt.gson.models.movies.MovieDetails;
import com.herring.yelt.gson.models.movies.MoviePopular;
import com.herring.yelt.gson.models.movies.MovieTopRated;
import com.herring.yelt.models.UserReview;
import com.herring.yelt.services.UserReviewService;
import com.herring.yelt.services.movies.MovieDetailsService;
import com.herring.yelt.services.movies.PopularMoviesService;
import com.herring.yelt.services.movies.TopRatedMovieService;
import com.herring.yelt.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomepageController {

    @Autowired
    private PopularMoviesService popularMoviesService;

    @Autowired
    private TopRatedMovieService topRatedMovieService;

    @Autowired
    private UserReviewService userReviewService;

    @Autowired
    private MovieDetailsService movieDetailsService;

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String home(Model model) {
        MoviePopular moviePopular = popularMoviesService.getPopularMovies();
        MovieTopRated topRatedMovies = topRatedMovieService.getTopRatedMovies();
        List<UserReview> userReviews = userReviewService.getLastReviews();
        List<String> ids = new ArrayList<>();
        for (UserReview review : userReviews) {
            ids.add(review.getMid());
        }
        List<MovieDetails> userReviewsMovieDetails = movieDetailsService.getMoviesById(ids);

        model.addAttribute("moviePopular", moviePopular);
        model.addAttribute("topRatedMovies", topRatedMovies);
        model.addAttribute("userReviews", userReviews);
        model.addAttribute("userReviewsMovieDetails", userReviewsMovieDetails);
        model.addAttribute("userService", userService);

        return "Homepage";
    }
}
