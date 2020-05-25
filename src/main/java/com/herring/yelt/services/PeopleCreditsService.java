package com.herring.yelt.services;

import com.herring.yelt.TMDbRequester;
import com.herring.yelt.gson.models.people.PeopleCredits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class PeopleCreditsService {

    @Autowired
    private TMDbRequester tmDbRequester;

    @Transactional
    public Map<String, List<PeopleCredits.Cast>> getStructuredCast(String pId) {
        PeopleCredits peopleCredits = tmDbRequester.getPeopleCredits(pId);

        Set<String> castYears = new TreeSet<>(); // Уникальные даты
        for (PeopleCredits.Cast cast : peopleCredits.cast) {
            if (cast.release_date == null) {
                cast.release_date = "—";
            }
            if (cast.release_date.length() != 0) {
                cast.release_date = cast.release_date.split("-")[0];
            } else {
                cast.release_date = "—";
            }
            castYears.add(cast.release_date);

        }

        Collections.sort(peopleCredits.cast);

        Map<String, List<PeopleCredits.Cast>> castMap = new TreeMap<>(Collections.reverseOrder());
        for (String castYear : castYears) {
            List<PeopleCredits.Cast> castArr = new ArrayList<>();
            for (PeopleCredits.Cast cast : peopleCredits.cast) {
                if (cast.release_date.equals(castYear)) {
                    castArr.add(cast);
                } else {
                    continue;
                }
            }
            castMap.put(castYear, castArr);
        }

        return castMap;
    }

    @Transactional
    public Map<String, List<PeopleCredits.Crew>> getStructuredCrew(String pId) {
        PeopleCredits peopleCredits = tmDbRequester.getPeopleCredits(pId);
        Set<String> crewYears = new TreeSet<>(); // Уникальные даты
        for (PeopleCredits.Crew crew : peopleCredits.crew) {
            if (crew.release_date == null) {
                crew.release_date = "—";
            }
            if (crew.release_date.length() != 0) {
                crew.release_date = crew.release_date.split("-")[0];
            } else {
                crew.release_date = "—";
            }
            crewYears.add(crew.release_date);
        }

        Collections.sort(peopleCredits.crew);

        Map<String, List<PeopleCredits.Crew>> crewMap = new TreeMap<>(Collections.reverseOrder());
        for (String crewYear : crewYears) {
            List<PeopleCredits.Crew> crewArr = new ArrayList<>();
            for (PeopleCredits.Crew crew : peopleCredits.crew) {
                if (crew.release_date.equals(crewYear)) {
                    crewArr.add(crew);
                } else {
                    continue;
                }
            }
            crewMap.put(crewYear, crewArr);
        }

        return crewMap;
    }

    @Transactional
    public int getKnownCreditsCount(String pId) {
        PeopleCredits peopleCredits = tmDbRequester.getPeopleCredits(pId);
        return peopleCredits.cast.size() + peopleCredits.crew.size();
    }

    @Transactional
    public PeopleCredits getCredits(String pId) {
        PeopleCredits peopleCredits = tmDbRequester.getPeopleCredits(pId);
        return peopleCredits;
    }

}










