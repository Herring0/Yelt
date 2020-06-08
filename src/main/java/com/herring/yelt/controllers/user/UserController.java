package com.herring.yelt.controllers.user;

import com.herring.yelt.gson.models.movies.MovieDetails;
import com.herring.yelt.models.User;
import com.herring.yelt.models.UserMovie;
import com.herring.yelt.models.Watchlist;
import com.herring.yelt.services.UserReviewService;
import com.herring.yelt.services.movies.MovieDetailsService;
import com.herring.yelt.services.user.UserMovieService;
import com.herring.yelt.services.user.UserService;
import com.herring.yelt.services.watchlist.WatchlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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

    @Autowired
    private WatchlistService watchlistService;

    @GetMapping("/{login}")
    public String main(@PathVariable(value = "login") String login, Model model) {
        User user = userService.getUserByLogin(login);
        Map<Integer, String> voteMapPercentage = userMovieService.getVotesMapPercentage(Integer.valueOf(user.getId()));
        Map<Integer, Integer> voteMapCount = userMovieService.getVotesMapCount(Integer.valueOf(user.getId()));
        List<Watchlist> watchlist = watchlistService.getWatchlistsByUserId(user.getId());

        List<UserMovie> latestVotes = userMovieService.getLatestVotesOrderedByDate(Integer.valueOf(user.getId()));
        List<String> latestIds = new ArrayList<>();
        for (UserMovie userMovie : latestVotes) {
            latestIds.add(userMovie.getMid());
        }

        List<String> ids = new ArrayList<>();
        for (Watchlist list : watchlist) {
            ids.add(list.getMid());
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
        model.addAttribute("watchlist", movieDetailsService.getMoviesById(ids));
        model.addAttribute("movieDetailsService", movieDetailsService);
        model.addAttribute("watchlistService", watchlistService);

        return "user/User";
    }

    @GetMapping("/{login}/votes")
    public String votes(@PathVariable(value = "login") String login, Model model) {
        User user = userService.getUserByLogin(login);
        List<UserMovie> votes = userMovieService.getVotesById(user.getId());
        List<String> ids = new ArrayList<>();
        for (UserMovie userMovie : votes) {
            ids.add(userMovie.getMid());
        }

        List<MovieDetails> movieDetails = movieDetailsService.getMoviesById(ids);

        model.addAttribute("user", user);
        model.addAttribute("votes", votes);
        model.addAttribute("movieDetails", movieDetails);
        model.addAttribute("authenticated", userService.getAuthenticatedUser());
        return "user/Votes";
    }

    @GetMapping("/{login}/watchlist")
    public String watchlist(@PathVariable(value = "login") String login, Model model) {
        User user = userService.getUserByLogin(login);
        List<Watchlist> watchlist = watchlistService.getWatchlistsByUserId(user.getId());
        List<String> ids = new ArrayList<>();
        for (Watchlist list : watchlist) {
            ids.add(list.getMid());
        }

        List<MovieDetails> movieDetails = movieDetailsService.getMoviesById(ids);

        model.addAttribute("user", user);
        model.addAttribute("watchlist", watchlist);
        model.addAttribute("movieDetails", movieDetails);
        model.addAttribute("authenticated", userService.getAuthenticatedUser());
        return "user/Watchlist";
    }

    @PostMapping("/{login}/block")
    @ResponseBody
    public void blockUser(@PathVariable(value = "login") String login) {
        User user = userService.getUserByLogin(login);
        user.setActive(false);
        userService.saveUser(user);
    }

    @PostMapping("/{login}/unblock")
    @ResponseBody
    public void unblockUser(@PathVariable(value = "login") String login) {
        User user = userService.getUserByLogin(login);
        user.setActive(true);
        userService.saveUser(user);
    }

}
