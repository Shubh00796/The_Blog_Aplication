package com.Personal.blogapplication.Repo;

import com.Personal.blogapplication.Entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {

}
