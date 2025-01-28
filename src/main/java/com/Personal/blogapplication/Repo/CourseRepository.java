package com.Personal.blogapplication.Repo;

import com.Personal.blogapplication.Entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
