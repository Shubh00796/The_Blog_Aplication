package com.Personal.blogapplication.Service;

import com.Personal.blogapplication.Dtos.ReservationDTO;
import com.Personal.blogapplication.Entity.Movie;
import com.Personal.blogapplication.Entity.Reservation;
import com.Personal.blogapplication.Entity.Showtime;
import com.Personal.blogapplication.Mappers.ReservationMapper;
import com.Personal.blogapplication.Repo.MovieRepository;
import com.Personal.blogapplication.Repo.ReservationRepository;
import com.Personal.blogapplication.Repo.ShowtimeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final MovieRepository movieRepository;
    private final ShowtimeRepository showtimeRepository;
    private final ReservationMapper reservationMapper;

    public ReservationDTO createReservation(Long movieId, Long showtimeId, ReservationDTO reservationDTO) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new RuntimeException("Movie with id " + movieId + " not found"));

        Showtime showtime = showtimeRepository.findById(showtimeId)
                .orElseThrow(() -> new RuntimeException("Showtime with id " + showtimeId + " not found"));

        if (!showtime.getMovieId().equals(movie.getId())) {
            throw new RuntimeException("Showtime with id " + showtimeId + " does not belong to movie with id " + movieId);
        }

        Set<Integer> requestedSeats = reservationDTO.getSeatNumbers();
        if (requestedSeats == null || requestedSeats.isEmpty()) {
            throw new RuntimeException("No seats selected for reservation");
        }

        if (requestedSeats.stream().anyMatch(seat -> seat < 1 || seat > showtime.getTotalSeats())) {
            throw new RuntimeException("One or more seat numbers are out of the valid range (1 to " + showtime.getTotalSeats() + ")");
        }

        if (showtime.getReservedSeats() == null) {
            showtime.setReservedSeats(new HashSet<>());
        }

        for (Integer seat : requestedSeats) {
            if (showtime.getReservedSeats().contains(seat)) {
                throw new RuntimeException("Seat " + seat + " is already reserved");
            }
        }

        showtime.getReservedSeats().addAll(requestedSeats);
        showtimeRepository.save(showtime);

        Reservation reservation = reservationMapper.toEntity(reservationDTO);
        reservation.setShowtimeId(String.valueOf(showtimeId));
        reservation.setReservationTime(LocalDateTime.now());

        Reservation savedReservation = reservationRepository.save(reservation);
        log.info("Reservation created with id {} for user {}.", savedReservation.getId(), savedReservation.getUserId());
        return reservationMapper.toDTO(savedReservation);
    }

    public void cancelReservation(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Reservation with id " + reservationId + " not found"));

        Showtime showtime = showtimeRepository.findById(Long.valueOf(reservation.getShowtimeId()))
                .orElseThrow(() -> new RuntimeException("Showtime with id " + reservation.getShowtimeId() + " not found"));

        if (showtime.getStartTime().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Cannot cancel reservation for a showtime that has already started or passed");
        }

        if (showtime.getReservedSeats() != null) {
            showtime.getReservedSeats().removeAll(reservation.getSeatNumbers());
            showtimeRepository.save(showtime);
        }

        reservationRepository.delete(reservation);
        log.info("Reservation with id {} has been cancelled.", reservationId);
    }

}
