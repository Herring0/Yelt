package com.herring.yelt.controllers;

import com.herring.yelt.models.User;
import com.herring.yelt.repositories.RoleRepository;
import com.herring.yelt.repositories.UserRepository;
import com.herring.yelt.services.UsersDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private RoleRepository roleRepository;

    @Autowired
    private UsersDataService usersDataService;

    @Autowired
    private PasswordEncoder passwordEncoder;

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
        user.setRoles(Collections.singleton(roleRepository.findById(1)));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        usersDataService.insertUser(user);

        return "redirect:/login";
    }
}
