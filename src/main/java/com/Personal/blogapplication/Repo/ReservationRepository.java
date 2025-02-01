package com.Personal.blogapplication.Repo;

import com.Personal.blogapplication.Entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
