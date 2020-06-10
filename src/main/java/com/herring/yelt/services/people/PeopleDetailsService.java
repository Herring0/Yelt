package com.herring.yelt.services.people;

import com.herring.yelt.utils.TMDbRequester;
import com.herring.yelt.gson.models.people.PeopleDetails;
import com.herring.yelt.gson.models.search.SearchPeople;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PeopleDetailsService {
    @Autowired
    private TMDbRequester tmDbRequester;

    public SearchPeople setUpDepartments(SearchPeople searchPeople) {
        List<Thread> threads = new ArrayList<>();
        for (SearchPeople.Result result : searchPeople.results) {
            threads.add(new Thread(() -> {
                PeopleDetails peopleDetails = tmDbRequester.getPeopleDetails(result.id);
                result.department = peopleDetails.known_for_department;
                result.biography = peopleDetails.biography;
            }));
        }

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return searchPeople;
    }
}
