package com.herring.yelt.controllers;

import com.herring.yelt.gson.models.movies.MoviePopular;
import com.herring.yelt.services.movies.PopularMoviesService;
import com.herring.yelt.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomepageController {

    @Autowired
    private PopularMoviesService popularMoviesService;

    @GetMapping("/homepage")
    public String home(Model model) {

        MoviePopular moviePopular = popularMoviesService.getPopularMovies();

        model.addAttribute("moviePopular", moviePopular);

        return "Homepage";
    }
}
