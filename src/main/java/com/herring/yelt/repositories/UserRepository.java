package com.herring.yelt.repositories;

import com.herring.yelt.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
    User findByLogin(String login);
    User findById(int id);
}
