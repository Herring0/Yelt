package com.herring.yelt.repositories;

import com.herring.yelt.models.UserReview;
import com.herring.yelt.models.Watchlist;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WatchlistRepository extends CrudRepository<Watchlist, Integer> {
    Watchlist findByUidAndMid(int uid, String mid);
    List<Watchlist> findByUidOrderByIdDesc(int uid);
    Long countByUid(int uid);
    Long deleteById(int id);
}
