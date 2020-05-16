package com.herring.yelt.services;

import com.herring.yelt.gson.models.movies.MovieCredits;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieCreditsService {

    public List<MovieCredits.Crew> getDirectors(MovieCredits movieCredits) {
        List<MovieCredits.Crew> directors = new ArrayList<>();
        for (MovieCredits.Crew crew : movieCredits.crew) {
            if (crew.department.equals("Directing")) {
                directors.add(crew);
            }
        }
        return directors;
    }

    public List<MovieCredits.Crew> getWriters(MovieCredits movieCredits) {
        List<MovieCredits.Crew> writers = new ArrayList<>();
        for (MovieCredits.Crew crew : movieCredits.crew) {
            OUTER_LOOP:
            if (crew.department.equals("Writing")) {
                for (MovieCredits.Crew writer : writers) {
                    if (writer.name.equals(crew.name))
                        break OUTER_LOOP;
                }
                writers.add(crew);
            }
        }
        return writers;
    }

    public List<MovieCredits.Crew> getProducers(MovieCredits movieCredits) {

        List<MovieCredits.Crew> producers = new ArrayList<>();
        for (MovieCredits.Crew crew : movieCredits.crew) {
            OUTER_LOOP:
            if (crew.department.equals("Production")) {
                for (MovieCredits.Crew producer : producers) {
                    if (producer.name.equals(crew.name))
                        break OUTER_LOOP;
                }
                producers.add(crew);
            }
        }
        return producers;
    }
    public List<MovieCredits.Crew> getCameras(MovieCredits movieCredits) {
        List<MovieCredits.Crew> cameras = new ArrayList<>();
        for (MovieCredits.Crew crew : movieCredits.crew) {
            OUTER_LOOP:
            if (crew.department.equals("Camera")) {
                for (MovieCredits.Crew camera : cameras) {
                    if (camera.name.equals(crew.name))
                        break OUTER_LOOP;
                }
                cameras.add(crew);
            }
        }
        return cameras;
    }
}
