package com.herring.yelt;

import com.google.gson.Gson;
import com.herring.yelt.gson.models.search.SearchMovies;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import static com.sun.xml.internal.ws.policy.sourcemodel.wspolicy.XmlToken.Uri;

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
        System.out.println(urlBuilder.toString());

        if (query.length() > 0) {
            String result = new RestTemplate().getForObject(urlBuilder.toString(), String.class);
            searchMovies = new Gson().fromJson(result, SearchMovies.class);
            return searchMovies;
        }

        return null;
    }

    public void closeConnection() {

    }
}
