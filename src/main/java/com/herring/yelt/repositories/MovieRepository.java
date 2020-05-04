package com.herring.yelt.repositories;

import com.herring.yelt.models.Movie;
import org.springframework.data.repository.CrudRepository;

public interface MovieRepository extends CrudRepository<Movie, String> {
}
