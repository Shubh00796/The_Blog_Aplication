package com.Personal.blogapplication.Repo;

import com.Personal.blogapplication.Entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
