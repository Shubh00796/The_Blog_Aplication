package com.Personal.blogapplication.Repo;

import com.Personal.blogapplication.Entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}