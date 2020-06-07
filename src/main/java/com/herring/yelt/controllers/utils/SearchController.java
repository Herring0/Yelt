package com.herring.yelt.controllers.utils;

import com.herring.yelt.TMDbSearchHandler;
import com.herring.yelt.gson.models.search.SearchMovies;
import com.herring.yelt.gson.models.search.SearchPeople;
import com.herring.yelt.services.people.PeopleDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class SearchController {

    @Autowired
    private TMDbSearchHandler tmDbSearchHandler;

    @Autowired
    private PeopleDetailsService peopleDetailsService;

    @GetMapping("/search")
    public String searchPage() {
        return "search/Search";
    }

    @PostMapping("/search/movies")
    @ResponseBody
    public SearchMovies searchMovies(@RequestParam("query") String query, Model model) {
        SearchMovies searchMovies = tmDbSearchHandler.searchMovies(query);
        model.addAttribute("searchMovies", searchMovies);

        return searchMovies;
    }

    @PostMapping("/search/people")
    @ResponseBody
    public SearchPeople searchPeople(@RequestParam("query") String query, Model model) {
        SearchPeople searchPeople = tmDbSearchHandler.searchPeople(query);
        peopleDetailsService.setUpDepartments(searchPeople);
        model.addAttribute("searchPeople", searchPeople);

        return searchPeople;
    }
}
