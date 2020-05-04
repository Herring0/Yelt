package com.herring.yelt;

import com.google.gson.Gson;
import com.herring.yelt.gson.models.genres.GenresMovieList;
import com.herring.yelt.gson.models.movies.*;
import com.herring.yelt.gson.models.people.PeopleDetails;
import com.herring.yelt.models.Movie;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

@Component("tmdb_requester")
public class TMDbRequester {

    @Value("${api_key}")
    private String apiKey;

    private final String BASE_URL = "https://api.themoviedb.org/3";

    public MovieDetails getMovieDetails(String id) {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        MovieDetails movieDetails;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(BASE_URL);
        stringBuilder.append("/movie/");
        stringBuilder.append(id);
        stringBuilder.append("?api_key=");
        stringBuilder.append(apiKey);

        String result = "";
        try {
            HttpGet request = new HttpGet(stringBuilder.toString());
            CloseableHttpResponse response = httpClient.execute(request);
            try {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    // return it as a String
                    result = EntityUtils.toString(entity);
                    System.out.println(result);
                }

            } finally {
                response.close();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        movieDetails = new Gson().fromJson(result, MovieDetails.class);
        System.out.println("Movie details received");
        return movieDetails;
    }

    public PeopleDetails getPeopleDetails(String id) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        PeopleDetails peopleDetails;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(BASE_URL);
        stringBuilder.append("/person/");
        stringBuilder.append(id);
        stringBuilder.append("?api_key=");
        stringBuilder.append(apiKey);
        String result = "";
        try {
            HttpGet request = new HttpGet(stringBuilder.toString());
            CloseableHttpResponse response = httpClient.execute(request);

            try {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    // return it as a String
                    result = EntityUtils.toString(entity);
                    System.out.println(result);
                }

            } finally {
                response.close();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        peopleDetails = new Gson().fromJson(result, PeopleDetails.class);
        System.out.println("People details received");
        return peopleDetails;
    }

    public MovieCredits getMovieCredits(String id) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        MovieCredits movieCredits;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(BASE_URL);
        stringBuilder.append("/movie/");
        stringBuilder.append(id);
        stringBuilder.append("/credits");
        stringBuilder.append("?api_key=");
        stringBuilder.append(apiKey);
        String result = "";
        try {
            HttpGet request = new HttpGet(stringBuilder.toString());
            CloseableHttpResponse response = httpClient.execute(request);

            try {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    // return it as a String
                    result = EntityUtils.toString(entity);
                    System.out.println(result);
                }

            } finally {
                response.close();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        movieCredits = new Gson().fromJson(result, MovieCredits.class);
        System.out.println("Movie credits received");
        return movieCredits;
    }

    public MovieReleaseDates getMovieReleaseDates(String id) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        MovieReleaseDates movieReleaseDates;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(BASE_URL);
        stringBuilder.append("/movie/");
        stringBuilder.append(id);
        stringBuilder.append("/release_dates");
        stringBuilder.append("?api_key=");
        stringBuilder.append(apiKey);
        String result = "";
        try {
            HttpGet request = new HttpGet(stringBuilder.toString());
            CloseableHttpResponse response = httpClient.execute(request);

            try {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    // return it as a String
                    result = EntityUtils.toString(entity);
                    System.out.println(result);
                }

            } finally {
                response.close();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        movieReleaseDates = new Gson().fromJson(result, MovieReleaseDates.class);
        System.out.println("Movie release dates received");
        return movieReleaseDates;
    }

    public MovieSimilarMovies getSimilarMovies(String id) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        MovieSimilarMovies movieSimilarMovies;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(BASE_URL);
        stringBuilder.append("/movie/");
        stringBuilder.append(id);
        stringBuilder.append("/similar");
        stringBuilder.append("?api_key=");
        stringBuilder.append(apiKey);
        stringBuilder.append("&page=1");
        String result = "";
        try {
            HttpGet request = new HttpGet(stringBuilder.toString());
            CloseableHttpResponse response = httpClient.execute(request);

            try {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    // return it as a String
                    result = EntityUtils.toString(entity);
                    System.out.println(result);
                }

            } finally {
                response.close();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        movieSimilarMovies = new Gson().fromJson(result, MovieSimilarMovies.class);
        System.out.println("Similar movies received");
        return movieSimilarMovies;
    }

    public GenresMovieList getGenresMovieList() {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        GenresMovieList genresMovieList;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(BASE_URL);
        stringBuilder.append("/genre/movie/list");
        stringBuilder.append("?api_key=");
        stringBuilder.append(apiKey);
        String result = "";
        try {
            HttpGet request = new HttpGet(stringBuilder.toString());
            CloseableHttpResponse response = httpClient.execute(request);

            try {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    result = EntityUtils.toString(entity);
                    System.out.println(result);
                }

            } finally {
                response.close();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        genresMovieList = new Gson().fromJson(result, GenresMovieList.class);
        System.out.println("Movie genres received");
        return genresMovieList;
    }

    public MovieVideos getMovieVideos(String id) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        MovieVideos movieVideos;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(BASE_URL);
        stringBuilder.append("/movie/");
        stringBuilder.append(id);
        stringBuilder.append("/videos");
        stringBuilder.append("?api_key=");
        stringBuilder.append(apiKey);
        String result = "";
        try {
            HttpGet request = new HttpGet(stringBuilder.toString());
            CloseableHttpResponse response = httpClient.execute(request);

            try {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    result = EntityUtils.toString(entity);
                    System.out.println(result);
                }

            } finally {
                response.close();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        movieVideos = new Gson().fromJson(result, MovieVideos.class);
        System.out.println("Movie videos received");
        return movieVideos;
    }
}
