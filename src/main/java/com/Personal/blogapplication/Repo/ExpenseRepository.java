package com.Personal.blogapplication.Repo;

import com.Personal.blogapplication.Entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense ,Long> {
    List<Expense> findByEmail(String email);
    List<Expense> findByCategory(String category);
}
