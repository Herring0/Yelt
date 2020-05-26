package com.herring.yelt.controllers;

import com.herring.yelt.TMDbSearchHandler;
import com.herring.yelt.gson.models.search.SearchMovies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class SearchController {

    @Autowired
    private TMDbSearchHandler tmDbSearchHandler;

    @PostMapping("/search")
    @ResponseBody
    public SearchMovies searchPost(@RequestParam("query") String query, Model model) {
//        tmDbSearchHandler.closeConnection();
        SearchMovies searchMovies = tmDbSearchHandler.searchMovies(query);
        if (searchMovies != null)
            System.out.println("total_results: " + searchMovies.total_results);
        model.addAttribute("searchMovies", searchMovies);
        return searchMovies;
    }
}
