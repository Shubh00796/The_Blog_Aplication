package com.Personal.blogapplication.Repo;

import com.Personal.blogapplication.Entity.Course;
import com.Personal.blogapplication.Entity.OnlineCourse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OnlineCourseRepository extends JpaRepository<OnlineCourse, Long> {
}
