package com.herring.yelt.services.people;

import com.herring.yelt.gson.models.movies.MoviePopular;
import com.herring.yelt.gson.models.people.PeoplePopular;
import com.herring.yelt.utils.TMDbRequester;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PopularPeopleService {

    @Autowired
    private TMDbRequester tmDbRequester;

    public PeoplePopular getPopularPeople() {
        return tmDbRequester.getPopularPeople();
    }
}
