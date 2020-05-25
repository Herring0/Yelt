package com.herring.yelt.controllers.people;

import com.herring.yelt.TMDbRequester;
import com.herring.yelt.gson.models.discover.DiscoverMovie;
import com.herring.yelt.gson.models.people.PeopleCredits;
import com.herring.yelt.gson.models.people.PeopleDetails;
import com.herring.yelt.services.PeopleCreditsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/people")
public class PeopleController {

    @Autowired
    private TMDbRequester tmDbRequester;

    @Autowired
    private PeopleCreditsService peopleCreditsService;

    @GetMapping("/{id}")
    public String main(@PathVariable(value = "id") String id, Model model) {
        peopleCreditsService.getStructuredCrew(id);

        PeopleDetails peopleDetails = tmDbRequester.getPeopleDetails(id);
        DiscoverMovie discoverMovies = tmDbRequester.getDiscoverMovies(id);

        model.addAttribute("people", peopleDetails);
        model.addAttribute("peopleCreditsService", peopleCreditsService);
        model.addAttribute("discoverMovies", discoverMovies);
        return "people/People";
    }
}
