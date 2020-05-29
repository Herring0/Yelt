package com.herring.yelt.controllers.user;

import com.herring.yelt.gson.models.discover.DiscoverMovie;
import com.herring.yelt.gson.models.people.PeopleDetails;
import com.herring.yelt.models.User;
import com.herring.yelt.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public String main(@PathVariable(value = "id") String id, Model model) {
        User user = userService.getUserById(Integer.valueOf(id));

        model.addAttribute("user", user);
        return "user/User";
    }

}
