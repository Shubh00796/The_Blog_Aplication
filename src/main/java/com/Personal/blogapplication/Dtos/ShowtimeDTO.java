package com.Personal.blogapplication.Dtos;

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
public class ShowtimeDTO {
    private Long id;
    private Long movieId;
    private LocalDateTime startTime;
    private int totalSeats;
    private Set<Integer> reservedSeats;
}
