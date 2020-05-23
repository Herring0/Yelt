package com.herring.yelt.repositories;

import com.herring.yelt.models.UserReview;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserReviewRepository extends CrudRepository<UserReview, Integer> {
    UserReview findByUidAndMid(int uid, String mid);
    List<UserReview> findByMid(String mid);
}
