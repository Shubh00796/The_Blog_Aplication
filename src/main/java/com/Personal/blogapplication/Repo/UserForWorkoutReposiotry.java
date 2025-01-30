package com.Personal.blogapplication.Repo;

import com.Personal.blogapplication.Entity.UserForWorkout;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserForWorkoutReposiotry extends JpaRepository<UserForWorkout,Long> {
    Optional<UserForWorkout> findByUsername(String username);

}
