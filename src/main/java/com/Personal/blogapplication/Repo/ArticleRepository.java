package com.Personal.blogapplication.Repo;

import com.Personal.blogapplication.Entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article ,Long> {
    List<Article> findAllByOrderByPublicationDateDesc();
}
