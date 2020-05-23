package com.herring.yelt.services;

import com.herring.yelt.repositories.UserReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserReviewService {

    @Autowired
    private UserReviewRepository userReviewRepository;

    @Transactional
    public Long getReviewCount(int uId) {
        return userReviewRepository.countByUid(uId);
    }

}
