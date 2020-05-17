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
import java.util.*;

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
        MovieVideos movieVideos = tmDbRequester.getMovieVideos(id);
        List<UserMovie> votes = userMovieRepository.findByMid(id);

        int votesCount = 0;
        String rating = "0";
        if (votes != null && votes.size() > 0) {
            votesCount = votes.size();
            int i = 0;
            for (UserMovie vote : votes) {
                i += vote.getRating();
            }
            float ratingI = (float) i / votesCount;
            rating = String.format("%.1f", ratingI).replace(",", ".");
        }

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails user;
        UserMovie userMovie = null;
        User myUser = null;
        if (principal instanceof UserDetails) {
            user = ((UserDetails)principal);
            myUser = userRepository.findByLogin(user.getUsername());
            userMovie = userMovieRepository.findByUidAndMid(myUser.getId(), id);
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
        model.addAttribute("votesCount", votesCount);
        model.addAttribute("rating", rating);

        return "movie/Movie";
    }

    @PostMapping("/{id}")
    @ResponseBody
    public Map<String, Object> rate(@PathVariable(value = "id") String id, @RequestParam(value="rating") int rating, @RequestParam(value="date") String date) {
        String jdate = null;
        Map<String, Object> modelMap = new HashMap<>();
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
            User userDB = userRepository.findByLogin(user.getUsername());
            userMovie = userMovieRepository.findByUidAndMid(userDB.getId(), id);
            if (rating == -1) {
                userMovieRepository.delete(userMovie);
                return null;
            }
            if (userMovie == null) {
                userMovie = new UserMovie(userDB.getId(), id, rating, jdate);
            } else {
                userMovie.setRating(rating);
                userMovie.setVote_time(jdate);
            }
            userMovieRepository.save(userMovie);
            modelMap.put("rating", rating);
            modelMap.put("date", jdate);
        } else {
//            return "redirect:/login";
        }

        return modelMap;
    }
}
