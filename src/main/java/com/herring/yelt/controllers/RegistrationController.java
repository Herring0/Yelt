package com.herring.yelt.controllers;

import com.herring.yelt.Role;
import com.herring.yelt.models.User;
import com.herring.yelt.repositories.UserRepository;
import com.herring.yelt.services.UsersDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UsersDataService usersDataService;

    @GetMapping("/registration")
    public String registration() {
        return "Registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model) {
        User userFromDb = userRepository.findByLogin(user.getLogin());

        if (userFromDb != null) {
            model.put("message", "User already exists");
            return "registration";
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        usersDataService.insertUser(user);

        return "redirect:/login";
    }
}
