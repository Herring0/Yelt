package com.herring.yelt.repositories;

import com.herring.yelt.models.UserReview;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserReviewRepository extends CrudRepository<UserReview, Integer> {
    UserReview findByUidAndMid(int uid, String mid);
    List<UserReview> findByMid(String mid);
    @Query(value = "SELECT * FROM user_review ORDER BY date DESC limit 0,4",
            nativeQuery = true)
    List<UserReview> findTop4OrderByDateDesc();
    Long countByUid(int uid);
    Long deleteById(int id);
}
