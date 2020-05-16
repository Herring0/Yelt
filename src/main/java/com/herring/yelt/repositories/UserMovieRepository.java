package com.herring.yelt.repositories;

import com.herring.yelt.models.User;
import com.herring.yelt.models.UserMovie;
import org.springframework.data.repository.CrudRepository;

public interface UserMovieRepository extends CrudRepository<UserMovie, Integer> {
    UserMovie findByUidAndMid(int uid, String mid);
}
