package com.herring.yelt.controllers.movie;

import com.herring.yelt.TMDbRequester;
import com.herring.yelt.gson.models.movies.*;
import com.herring.yelt.gson.models.people.PeopleCredits;
import com.herring.yelt.models.User;
import com.herring.yelt.models.UserMovie;
import com.herring.yelt.models.UserReview;
import com.herring.yelt.repositories.UserMovieRepository;
import com.herring.yelt.repositories.UserRepository;
import com.herring.yelt.repositories.UserReviewRepository;
import com.herring.yelt.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    private UserReviewRepository userReviewRepository;

    @Autowired
    private UsersDataService usersDataService;
    @Autowired
    private UserReviewService userReviewService;
    @Autowired
    private MovieCreditsService movieCreditsService;

    @Autowired
    private TMDbRequester tmDbRequester;

    @Autowired
    private MovieSimilarMoviesService movieSimilarMoviesService;
    @Autowired
    private MovieCertificationService movieCertificationService;

    private MovieDetails movieDetails;
    private MovieCredits movieCredits;
    private MovieReleaseDates movieReleaseDates;
    private MovieSimilarMovies similarMovies;
    private MovieVideos movieVideos;
    private MovieLists movieLists;

    @GetMapping("/{id}")
    public String main(@PathVariable(value = "id") String id, Model model) {
        executeTMDbRequester(id);
        List<UserMovie> votes = userMovieRepository.findByMid(id);
        List<UserReview> reviews = userReviewRepository.findByMid(id);

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
        UserMovie userMovie = null;
        User myUser = null;
        if (principal instanceof UserDetails) {
            UserDetails user = ((UserDetails)principal);
            myUser = userRepository.findByLogin(user.getUsername());
            userMovie = userMovieRepository.findByUidAndMid(myUser.getId(), id);

            // Move user review to first position
            UserReview userReview = userReviewRepository.findByUidAndMid(myUser.getId(), id);
            if (userReview != null) {
                int index = reviews.indexOf(userReview);
                reviews.remove(index);
                reviews.add(0, userReview);
            }
        }

        movieDetails.release_date = movieDetails.release_date.split("-")[0];
        movieDetails.revenue_in_dollars = NumberFormat.getCurrencyInstance(Locale.US).format(movieDetails.revenue);

        model.addAttribute("movie", movieDetails);
        model.addAttribute("credits", movieCredits);
        model.addAttribute("directors", movieCreditsService.getDirectors(movieCredits));
        model.addAttribute("writers", movieCreditsService.getWriters(movieCredits));
        model.addAttribute("producers", movieCreditsService.getProducers(movieCredits));
        model.addAttribute("cameras", movieCreditsService.getCameras(movieCredits));
        model.addAttribute("crewSet", movieCreditsService.getCrewSet(movieCredits));
        model.addAttribute("certification", movieCertificationService.getCertification(movieReleaseDates));
        model.addAttribute("similarMovies", movieSimilarMoviesService.getSimilarMovies(similarMovies));
        model.addAttribute("movieVideos", movieVideos);
        model.addAttribute("user", myUser);
        model.addAttribute("userMovie", userMovie);
        model.addAttribute("votesCount", votesCount);
        model.addAttribute("rating", rating);
        model.addAttribute("movieLists", movieLists);
        model.addAttribute("reviews", reviews);
        model.addAttribute("usersDataService", usersDataService);
        model.addAttribute("userReviewService", userReviewService);

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
        if (principal instanceof UserDetails) {
            UserDetails user = ((UserDetails)principal);
            User userDB = userRepository.findByLogin(user.getUsername());
            UserMovie userMovie = userMovieRepository.findByUidAndMid(userDB.getId(), id);
            if (rating == -1) {
                userMovieRepository.delete(userMovie);
                return null;
            }
            if (userMovie == null) {
                userMovie = new UserMovie(userDB.getId(), id, rating, jdate);
                userDB.setNum_rating(userDB.getNum_rating() + 1);
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

    @PostMapping(params="action=Write", path = "/{id}")
    public String writeReview(UserReview userReview, @PathVariable(value = "id") String id) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserReview userReviewDB;
        if (principal instanceof UserDetails) {
            UserDetails user = ((UserDetails)principal);
            User userDB = userRepository.findByLogin(user.getUsername());
            userReviewDB = userReviewRepository.findByUidAndMid(userDB.getId(), id);

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();

            if (userReviewDB == null) {
                userReviewDB = new UserReview(userDB.getId(), id, userReview.getType(), formatter.format(date), userReview.getText());
            } else {
                userReviewDB.setText(userReview.getText());
                userReviewDB.setDate(formatter.format(date));
                userReviewDB.setType(userReview.getType());
            }
            userReviewRepository.save(userReviewDB);
        }
        return "redirect:/movie/" + id;
    }

    @PostMapping("/{id}/delete_review")
    public String deleteVote(@PathVariable(value = "id") String id) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            UserDetails user = ((UserDetails)principal);
            User userDb = userRepository.findByLogin(user.getUsername());
            UserReview userReview = userReviewRepository.findByUidAndMid(userDb.getId(), id);
            if (userReview != null) {
                userReviewRepository.deleteById(userReview.getId());
            }
        }

        return "redirect:/movie/" + id;
    }


    // гениальный мув от гениального человека
    private void executeTMDbRequester(String id) {
        Thread detailsThread = new Thread(() -> movieDetails = tmDbRequester.getMovieDetails(id));
        Thread creditsThread = new Thread(() -> movieCredits = tmDbRequester.getMovieCredits(id));
        Thread releaseDatesThread = new Thread(() -> movieReleaseDates = tmDbRequester.getMovieReleaseDates(id));
        Thread similarMoviesThread = new Thread(() -> similarMovies = tmDbRequester.getSimilarMovies(id));
        Thread videosThread = new Thread(() -> movieVideos = tmDbRequester.getMovieVideos(id));
        Thread listsThread = new Thread(() -> movieLists = tmDbRequester.getMovieLists(id));

        detailsThread.start(); creditsThread.start();
        releaseDatesThread.start(); similarMoviesThread.start();
        videosThread.start(); listsThread.start();

        try {
            detailsThread.join(); creditsThread.join();
            releaseDatesThread.join(); similarMoviesThread.join();
            videosThread.join(); listsThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
