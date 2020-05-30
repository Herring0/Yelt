package com.herring.yelt.services.user;

import com.herring.yelt.models.UserMovie;
import com.herring.yelt.repositories.UserMovieRepository;
import com.herring.yelt.repositories.UserReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserMovieService {

    @Autowired
    private UserMovieRepository userMovieRepository;

    public Map<Integer, String> getVotesMapPercentage(int uId) {
        List<Integer> votes = new ArrayList<>();
        for (UserMovie userMovie : userMovieRepository.findByUid(uId)) {
            votes.add(userMovie.getRating());
        };
        Map<Integer, String> votesMap = new LinkedHashMap<>();
        for (int i = 1; i <= 10; i++) {
            float occurrences = Collections.frequency(votes, i);

            float percentage = (occurrences / votes.size()) * 100;
            votesMap.put(i , String.format("%.1f", percentage).replace(",", ".") + "%");
        }
        return votesMap;
    }

    public Map<Integer, Integer> getVotesMapCount(int uId) {
        List<Integer> votes = new ArrayList<>();
        for (UserMovie userMovie : userMovieRepository.findByUid(uId)) {
            votes.add(userMovie.getRating());
        };
        Map<Integer, Integer> votesMap = new LinkedHashMap<>();
        for (int i = 1; i <= 10; i++) {
            int occurrences = Collections.frequency(votes, i);
            votesMap.put(i , occurrences);
        }
        return votesMap;
    }

    public Long getCountById(int uId) {
        return userMovieRepository.countByUid(uId);
    }

    public float getAvgRatingById(int uId) {
        List<UserMovie> votes = userMovieRepository.findByUid(uId);
        float avgRating;
        float ratingSum = 0;
        for (UserMovie vote: votes) {
            ratingSum += vote.getRating();
        }
        if (ratingSum == 0)
            avgRating = 0;
        else
            avgRating = ratingSum / votes.size();
        return avgRating;
    }
}
