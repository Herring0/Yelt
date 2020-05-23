package com.herring.yelt.services;

import com.herring.yelt.models.User;
import com.herring.yelt.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsersDataService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public User insertUser(User user) {
        userRepository.save(user);
        return user;
    }

    @Transactional
    public User getUser(int id) {
        return userRepository.findById(id);
    }
}
