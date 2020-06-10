package com.herring.yelt.controllers.utils;

import com.herring.yelt.gson.models.movies.MovieDetails;
import com.herring.yelt.services.movies.MovieDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private MovieDetailsService movieDetailsService;

    @GetMapping("/movie")
    @ResponseBody
    public MovieDetails movie(@RequestParam(value = "id") String id) {
        MovieDetails movieDetails = movieDetailsService.getMovieById(id);
        return movieDetails;
    }
}
