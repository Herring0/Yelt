package com.herring.yelt.repositories;

import com.herring.yelt.models.UserMovie;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserMovieRepository extends CrudRepository<UserMovie, Integer> {
    UserMovie findByUidAndMid(int uid, String mid);
    List<UserMovie> findByMid(String mid);
}