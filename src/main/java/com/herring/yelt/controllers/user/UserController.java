package com.herring.yelt.controllers.user;

import com.herring.yelt.gson.models.discover.DiscoverMovie;
import com.herring.yelt.gson.models.people.PeopleDetails;
import com.herring.yelt.models.User;
import com.herring.yelt.models.UserMovie;
import com.herring.yelt.services.UserReviewService;
import com.herring.yelt.services.movies.MovieDetailsService;
import com.herring.yelt.services.user.UserMovieService;
import com.herring.yelt.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMovieService userMovieService;

    @Autowired
    private MovieDetailsService movieDetailsService;

    @Autowired
    private UserReviewService userReviewService;

    @GetMapping("/{login}")
    public String main(@PathVariable(value = "login") String login, Model model) {
        User user = userService.getUserByLogin(login);
        Map<Integer, String> voteMapPercentage = userMovieService.getVotesMapPercentage(Integer.valueOf(user.getId()));
        Map<Integer, Integer> voteMapCount = userMovieService.getVotesMapCount(Integer.valueOf(user.getId()));
        List<UserMovie> latestVotes = userMovieService.getLatestVotesOrderedByDate(Integer.valueOf(user.getId()));
        List<String> latestIds = new ArrayList<>();
        for (UserMovie userMovie : latestVotes) {
            latestIds.add(userMovie.getMid());
        }

        model.addAttribute("user", user);
        model.addAttribute("authenticated", userService.getAuthenticatedUser());
        model.addAttribute("userMovieService", userMovieService);
        model.addAttribute("avgRating", String.format("%.1f", userMovieService.getAvgRatingById(Integer.valueOf(user.getId()))).replace(",", "."));
        model.addAttribute("voteMapPercentage", voteMapPercentage);
        model.addAttribute("voteMapCount", voteMapCount);
        model.addAttribute("reviewCount", userReviewService.getReviewCount(Integer.valueOf(user.getId())));
        model.addAttribute("latestVotes", latestVotes);
        model.addAttribute("latestMovies", movieDetailsService.getMoviesById(latestIds));
        System.out.println(movieDetailsService.getMoviesById(latestIds));
        System.out.println(userMovieService.getLatestVotesOrderedByDate(Integer.valueOf(user.getId())));
        return "user/User";
    }

}
