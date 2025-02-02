package com.Personal.blogapplication.Repo;

import com.Personal.blogapplication.Entity.LeaderboardEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LeaderboardEntryRepository extends JpaRepository<LeaderboardEntry, Long> {

    Optional<LeaderboardEntry> findByUserId(String userId);


}
