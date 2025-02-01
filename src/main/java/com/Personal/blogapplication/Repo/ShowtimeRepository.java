package com.Personal.blogapplication.Repo;

import com.Personal.blogapplication.Entity.Showtime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowtimeRepository extends JpaRepository<Showtime, Long> {
}