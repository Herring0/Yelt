package com.herring.yelt.services.user;

import com.herring.yelt.models.User;
import com.herring.yelt.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User getUserById(int id) {
        return userRepository.findById(id);
    }

    public User getUserByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    public User getAuthenticatedUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = null;
        if (principal instanceof UserDetails) {
            userDetails = (UserDetails) principal;
            System.out.println("Authorities : " + userDetails.getAuthorities());
            return userRepository.findByLogin(userDetails.getUsername());
        }
        return null;
    }
}
