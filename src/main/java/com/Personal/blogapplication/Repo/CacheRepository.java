package com.Personal.blogapplication.Repo;

import com.Personal.blogapplication.Entity.CacheEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface CacheRepository extends JpaRepository<CacheEntry, Long> {
    Optional<CacheEntry> findByRequestPath(String requestPath);
}