package com.herring.yelt.controllers;

import com.herring.yelt.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomepageController {

    @Autowired
    private UserService userService;

    @GetMapping("/homepage")
    public String home(Model model) {

        model.addAttribute("authenticated", userService.getAuthenticatedUser());
        return "Homepage";
    }
}
