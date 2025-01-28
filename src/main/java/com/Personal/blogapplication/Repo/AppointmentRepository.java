package com.Personal.blogapplication.Repo;

import com.Personal.blogapplication.Entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}
