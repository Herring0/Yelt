package com.herring.yelt.controllers.review;

import com.herring.yelt.gson.models.movies.MovieDetails;
import com.herring.yelt.models.User;
import com.herring.yelt.models.UserReview;
import com.herring.yelt.services.UserReviewService;
import com.herring.yelt.services.UsersDataService;
import com.herring.yelt.services.movies.MovieDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    private UserReviewService userReviewService;

    @Autowired
    private UsersDataService usersDataService;

    @Autowired
    private MovieDetailsService movieDetailsService;

    @GetMapping("/{id}")
    public String main(@PathVariable(value = "id") String id, Model model) {
        UserReview userReview = userReviewService.getReview(Integer.valueOf(id));
        MovieDetails movieDetails = movieDetailsService.getMovieById(userReview.getMid());
        User user = usersDataService.getUser(userReview.getUid());

        model.addAttribute("userReview", userReview);
        model.addAttribute("movieDetails", movieDetails);
        model.addAttribute("user", user);

        return "review/Review";
    }
}
