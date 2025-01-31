package com.Personal.blogapplication.Repo;

import com.Personal.blogapplication.Entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> { // Use Long for ID

    Optional<Seat> findBySeatNumber(String seatNumber);


}