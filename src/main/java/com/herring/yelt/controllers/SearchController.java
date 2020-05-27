package com.herring.yelt.controllers;

import com.herring.yelt.TMDbSearchHandler;
import com.herring.yelt.gson.models.search.SearchMovies;
import com.herring.yelt.gson.models.search.SearchPeople;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class SearchController {

    @Autowired
    private TMDbSearchHandler tmDbSearchHandler;

    @PostMapping("/search/movies")
    @ResponseBody
    public SearchMovies searchMovies(@RequestParam("query") String query, Model model) {
        SearchMovies searchMovies = tmDbSearchHandler.searchMovies(query);
        model.addAttribute("searchMovies", searchMovies);

        return searchMovies;
    }

    @PostMapping("/search/peoples")
    @ResponseBody
    public SearchPeople searchPeople(@RequestParam("query") String query, Model model) {
        SearchPeople searchPeople = tmDbSearchHandler.searchPeople(query);
        model.addAttribute("searchPeople", searchPeople);

        return searchPeople;
    }
}
