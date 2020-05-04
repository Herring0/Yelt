package com.herring.yelt.controllers;

import com.herring.yelt.TMDbRequester;
import com.herring.yelt.gson.models.movies.MovieDetails;
import com.herring.yelt.models.Movie;
import com.herring.yelt.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MainController {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ApplicationContext appContext;

//    @GetMapping("/movie/{id}")
//    public String main(@PathVariable(value = "id") String id, Model model) {
//        TMDbRequester tmDbRequester = appContext.getBean("tmdb_requester", TMDbRequester.class);
//        Iterable<Movie> movies = movieRepository.findAll();
//        MovieDetails movieDetails = tmDbRequester.getMovieDetails(id);
//        model.addAttribute("title", movieDetails.title);
//        model.addAttribute("overview", movieDetails.overview);
//        model.addAttribute("budget", movieDetails.budget);
//        System.out.println(movieDetails.overview);
//        return "Main";
//    }
}
