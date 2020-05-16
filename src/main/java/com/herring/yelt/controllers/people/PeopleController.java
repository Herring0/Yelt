package com.herring.yelt.controllers.people;

import com.herring.yelt.TMDbRequester;
import com.herring.yelt.gson.models.people.PeopleDetails;
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

    @GetMapping("/{id}")
    public String main(@PathVariable(value = "id") String id, Model model) {
        PeopleDetails peopleDetails = tmDbRequester.getPeopleDetails(id);
        model.addAttribute("people", peopleDetails);
        return "people/People";
    }
}
