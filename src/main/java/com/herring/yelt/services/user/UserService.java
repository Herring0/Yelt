package com.herring.yelt.services.user;

import com.herring.yelt.models.User;
import com.herring.yelt.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User getUserById(int id) {
        return userRepository.findById(id);
    }
}
