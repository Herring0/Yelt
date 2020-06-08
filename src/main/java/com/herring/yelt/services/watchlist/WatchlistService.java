package com.herring.yelt.services.watchlist;

import com.herring.yelt.models.Watchlist;
import com.herring.yelt.repositories.WatchlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WatchlistService {

    @Autowired
    private WatchlistRepository watchlistRepository;

    public Watchlist getWatchlist(int uId, String mId) {
        return watchlistRepository.findByUidAndMid(uId, mId);
    }

    public List<Watchlist> getWatchlistsByUserId(int uId) {
        return watchlistRepository.findByUidOrderByIdDesc(uId);
    }

    public void save(Watchlist watchlist) {
        watchlistRepository.save(watchlist);
    }

    public Long getCountByUid(int uId) {
        return watchlistRepository.countByUid(uId);
    }

    public Long deleteById(int id) {
        return watchlistRepository.deleteById(id);
    }

}
