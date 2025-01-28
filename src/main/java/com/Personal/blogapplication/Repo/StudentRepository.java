package com.Personal.blogapplication.Repo;

import com.Personal.blogapplication.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
