package com.herring.yelt;

import com.google.gson.Gson;
import com.herring.yelt.gson.models.search.SearchMovies;
import com.herring.yelt.gson.models.search.SearchPeople;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component("tmdb_search_handler")
public class TMDbSearchHandler {

    @Value("${api_key}")
    private String apiKey;

    private final String BASE_URL = "https://api.themoviedb.org/3/search";

    private CloseableHttpClient httpClient;

    public SearchMovies searchMovies(String query) {
        RestTemplate restTemplate = new RestTemplate();
        SearchMovies searchMovies;

        StringBuilder urlBuilder = new StringBuilder();
        urlBuilder.append(BASE_URL);
        urlBuilder.append("/movie");
        urlBuilder.append("?api_key=");
        urlBuilder.append(apiKey);
        urlBuilder.append("&query=");
        urlBuilder.append(query);
        urlBuilder.append("&include_adult=true");

        if (query.length() > 0) {
            String result = new RestTemplate().getForObject(urlBuilder.toString(), String.class);
            searchMovies = new Gson().fromJson(result, SearchMovies.class);
            searchMovies.results.forEach(e -> e.dataType = "movie");
            return searchMovies;
        }

        return null;
    }

    public SearchPeople searchPeople(String query) {
        RestTemplate restTemplate = new RestTemplate();
        SearchPeople searchPeople;

        StringBuilder urlBuilder = new StringBuilder();
        urlBuilder.append(BASE_URL);
        urlBuilder.append("/person");
        urlBuilder.append("?api_key=");
        urlBuilder.append(apiKey);
        urlBuilder.append("&query=");
        urlBuilder.append(query);
        urlBuilder.append("&include_adult=true");

        if (query.length() > 0) {
            String result = new RestTemplate().getForObject(urlBuilder.toString(), String.class);
            searchPeople = new Gson().fromJson(result, SearchPeople.class);
            searchPeople.results.forEach(e -> e.dataType = "people");
            return searchPeople;
        }

        return null;
    }

}
