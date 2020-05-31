package com.herring.yelt.repositories;

import com.herring.yelt.models.UserMovie;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserMovieRepository extends CrudRepository<UserMovie, Integer> {
    UserMovie findByUidAndMid(int uid, String mid);
    List<UserMovie> findByMid(String mid);
    List<UserMovie> findByUid(int uid);
    List<UserMovie> findByUidOrderByVoteTimeDesc(int uid);
    List<UserMovie> findTop7ByUidOrderByVoteTimeDesc(int uid);
    Long countByUid(int uid);
}
