package com.Personal.blogapplication.Repo;

import com.Personal.blogapplication.Entity.UrlEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlRepository extends JpaRepository<UrlEntity, Long> {
    UrlEntity findByShortCode(String shortCode);
}