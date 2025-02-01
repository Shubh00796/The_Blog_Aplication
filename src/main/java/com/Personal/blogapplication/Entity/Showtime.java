package com.Personal.blogapplication.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "showtimes")
public class Showtime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "movie_id", nullable = false)
    private Long movieId;

    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "total_seats", nullable = false)
    private int totalSeats;

    @ElementCollection
    @CollectionTable(name = "showtime_reserved_seats", joinColumns = @JoinColumn(name = "showtime_id"))
    @Column(name = "seat_number")
    private Set<Integer> reservedSeats;

}