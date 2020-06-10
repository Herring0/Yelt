package com.herring.yelt.controllers;

import com.herring.yelt.gson.models.movies.MovieDetails;
import com.herring.yelt.gson.models.movies.MoviePopular;
import com.herring.yelt.gson.models.movies.MovieTopRated;
import com.herring.yelt.gson.models.people.PeoplePopular;
import com.herring.yelt.models.UserMovie;
import com.herring.yelt.models.UserReview;
import com.herring.yelt.services.UserReviewService;
import com.herring.yelt.services.movies.MovieDetailsService;
import com.herring.yelt.services.movies.PopularMoviesService;
import com.herring.yelt.services.movies.TopRatedMovieService;
import com.herring.yelt.services.people.PopularPeopleService;
import com.herring.yelt.services.user.UserMovieService;
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

    @Autowired
    private UserMovieService userMovieService;

    @Autowired
    private PopularPeopleService popularPeopleService;

    @GetMapping("/")
    public String home(Model model) {
        MoviePopular moviePopular = popularMoviesService.getPopularMovies();
        PeoplePopular peoplePopular = popularPeopleService.getPopularPeople();
        MovieTopRated topRatedMovies = topRatedMovieService.getTopRatedMovies();
        List<UserReview> userReviews = userReviewService.getLastReviews();
        List<UserMovie> topRated = userMovieService.getTop7RatedMovies();

        List<String> lastReviewsIds = new ArrayList<>();
        for (UserReview review : userReviews) {
            lastReviewsIds.add(review.getMid());
        }
        List<MovieDetails> userReviewsMovieDetails = movieDetailsService.getMoviesById(lastReviewsIds);

        List<String> topRatedMoviesIds = new ArrayList<>();
        for (UserMovie userMovie : topRated) {
            topRatedMoviesIds.add(userMovie.getMid());
        }

        List<MovieDetails> topRatedMovieDetails = movieDetailsService.getMoviesById(topRatedMoviesIds);
        peoplePopular.results.forEach(e -> System.out.println(e.name));

        model.addAttribute("moviePopular", moviePopular);
        model.addAttribute("peoplePopular", peoplePopular);
        model.addAttribute("topRatedMovies", topRatedMovieDetails);
        model.addAttribute("userReviews", userReviews);
        model.addAttribute("userReviewsMovieDetails", userReviewsMovieDetails);
        model.addAttribute("userService", userService);
//        model.addAttribute("topRatedMovieDetails", topRatedMovieDetails);

        return "Homepage";
    }
}
