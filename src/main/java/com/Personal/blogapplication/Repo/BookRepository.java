package com.Personal.blogapplication.Repo;

import com.Personal.blogapplication.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
