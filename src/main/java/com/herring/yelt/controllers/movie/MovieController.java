package com.herring.yelt.controllers.movie;

import com.herring.yelt.TMDbRequester;
import com.herring.yelt.gson.models.genres.GenresMovieList;
import com.herring.yelt.gson.models.movies.*;
import com.herring.yelt.models.User;
import com.herring.yelt.models.UserMovie;
import com.herring.yelt.repositories.UserMovieRepository;
import com.herring.yelt.repositories.UserRepository;
import com.herring.yelt.services.MovieCertificationService;
import com.herring.yelt.services.MovieCreditsService;
import com.herring.yelt.services.MovieSimilarMoviesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Controller
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private UserMovieRepository userMovieRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TMDbRequester tmDbRequester;

    @Autowired
    private MovieCreditsService movieCreditsService;
    @Autowired
    private MovieSimilarMoviesService movieSimilarMoviesService;
    @Autowired
    private MovieCertificationService movieCertificationService;

    @GetMapping("/{id}")
    public String main(@PathVariable(value = "id") String id, Model model) {
        MovieDetails movieDetails = tmDbRequester.getMovieDetails(id);
        MovieCredits movieCredits = tmDbRequester.getMovieCredits(id);
        MovieReleaseDates movieReleaseDates = tmDbRequester.getMovieReleaseDates(id);
        MovieSimilarMovies similarMovies = tmDbRequester.getSimilarMovies(id);
        GenresMovieList genresMovieList = tmDbRequester.getGenresMovieList();
        MovieVideos movieVideos = tmDbRequester.getMovieVideos(id);

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails user;
        UserMovie userMovie = null;
        User myUser = null;
        if (principal instanceof UserDetails) {
            user = ((UserDetails)principal);
            myUser = userRepository.findByLogin(user.getUsername());
            System.out.println(myUser.getId() + " | " + id);
            userMovie = userMovieRepository.findByUidAndMid(myUser.getId(), id);
            if (userMovie != null)
                System.out.println(userMovie.getRating());
            else
                System.out.println("um not found");
        }

        movieDetails.release_date = movieDetails.release_date.split("-")[0];
        movieDetails.revenue_in_dollars = NumberFormat.getCurrencyInstance(Locale.US).format(movieDetails.revenue);

        model.addAttribute("movie", movieDetails);
        model.addAttribute("credits", movieCredits);
        model.addAttribute("directors", movieCreditsService.getDirectors(movieCredits));
        model.addAttribute("writers", movieCreditsService.getWriters(movieCredits));
        model.addAttribute("producers", movieCreditsService.getProducers(movieCredits));
        model.addAttribute("cameras", movieCreditsService.getCameras(movieCredits));
        model.addAttribute("certification", movieCertificationService.getCertification(movieReleaseDates));
        model.addAttribute("similarMovies", movieSimilarMoviesService.getSimilarMovies(similarMovies));
        model.addAttribute("movieVideos", movieVideos);
        model.addAttribute("user", myUser);
        model.addAttribute("userMovie", userMovie);

        return "movie/Movie";
    }

    @PostMapping("/{id}")
    public String rate(@PathVariable(value = "id") String id, @RequestParam(value="rating") int rating, @RequestParam(value="date") String date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        String jdate = null;
        try {
            Date parsedDate = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").parse(date);
            jdate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(parsedDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails user = null;
        UserMovie userMovie = null;
        if (principal instanceof UserDetails) {
            user = ((UserDetails)principal);
            User myUser = userRepository.findByLogin(user.getUsername());
            userMovie = userMovieRepository.findByUidAndMid(myUser.getId(), id);
            if (userMovie == null) {
                userMovie = new UserMovie(myUser.getId(), id, rating, jdate);
                userMovieRepository.save(userMovie);
            } else {
                userMovie.setRating(rating);
                userMovie.setVote_time(jdate);
                userMovieRepository.save(userMovie);
            }

        } else {
            return "redirect:/login";
        }

        System.out.println("added user_movie with param: \n" +
                "\tuid = " + userMovie.getUid() + "\n" +
                "\tmid = " + userMovie.getMid() + "\n" +
                "\trating = " + userMovie.getRating() + "\n" +
                "\tdatetime = " + userMovie.getVote_time());
        return "redirect:/";
    }
}
