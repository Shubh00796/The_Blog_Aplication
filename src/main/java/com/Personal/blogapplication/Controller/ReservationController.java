package com.Personal.blogapplication.Controller;

import com.Personal.blogapplication.Dtos.ReservationDTO;
import com.Personal.blogapplication.Service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping("/movies/{movieId}/showtimes/{showtimeId}/reservations")
    public ResponseEntity<ReservationDTO> createReservation(
            @PathVariable Long movieId,
            @PathVariable Long showtimeId,
            @RequestBody ReservationDTO reservationDTO) {

        ReservationDTO createdReservation = reservationService.createReservation(movieId, showtimeId, reservationDTO);
        return new ResponseEntity<>(createdReservation, HttpStatus.CREATED);
    }

    @DeleteMapping("/reservations/{reservationId}")
    public ResponseEntity<Void> cancelReservation(@PathVariable Long reservationId) {
        reservationService.cancelReservation(reservationId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}