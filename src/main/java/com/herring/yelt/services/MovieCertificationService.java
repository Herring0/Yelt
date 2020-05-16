package com.herring.yelt.services;

import com.herring.yelt.gson.models.movies.MovieReleaseDates;
import org.springframework.stereotype.Service;

@Service
public class MovieCertificationService {
    public String getCertification(MovieReleaseDates movieReleaseDates) {
        String certification = "";
        for (MovieReleaseDates.Results result : movieReleaseDates.results) {
            if (result.iso_3166_1.equals("US")) {
                certification = result.release_dates.get(0).certification;
                break;
            }
        }
        return certification;
    }
}
