package com.herring.yelt.repositories;

import com.herring.yelt.models.UserMovie;
import com.herring.yelt.models.UserReview;
import org.springframework.data.repository.CrudRepository;

public interface UserReviewRepository extends CrudRepository<UserReview, Integer> {
}
