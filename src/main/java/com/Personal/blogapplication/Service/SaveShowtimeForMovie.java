package com.Personal.blogapplication.Service;

import com.Personal.blogapplication.Dtos.ShowtimeDTO;
import com.Personal.blogapplication.Entity.Movie;
import com.Personal.blogapplication.Entity.Showtime;
import com.Personal.blogapplication.Mappers.ShowtimeMapper;
import com.Personal.blogapplication.Repo.MovieRepository;
import com.Personal.blogapplication.Repo.ShowtimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SaveShowtimeForMovie {
    private final MovieRepository movieRepository;
    private final ShowtimeRepository showtimeRepository;
    private final ShowtimeMapper showtimeMapper;

    public ShowtimeDTO saveShowtimeForMovie(Long movieId, ShowtimeDTO showtimeDTO) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new RuntimeException("Movie name with given id not found"));

        Showtime entity = showtimeMapper.toEntity(showtimeDTO);
        entity.setMovieId(movie.getId());
        Showtime savedToEntity = showtimeRepository.save(entity);
        return showtimeMapper.toDTO(savedToEntity);

    }

    public ShowtimeDTO updateShowTime(Long showtimeId, ShowtimeDTO showtimeDTO) {
        Showtime showtime = showtimeRepository.findById(showtimeId) // Find by showtimeId
                .orElseThrow(() -> new RuntimeException("Showtime with given id not found"));

        showtime.setStartTime(showtimeDTO.getStartTime());
        showtime.setTotalSeats(showtimeDTO.getTotalSeats());

        if (showtimeDTO.getReservedSeats() != null) {
            showtime.setReservedSeats(new HashSet<>(showtimeDTO.getReservedSeats())); // Create a new Set
        }

        Showtime updatedShowtime = showtimeRepository.save(showtime);
        return showtimeMapper.toDTO(updatedShowtime);

    }


    public List<ShowtimeDTO> getShowtimesForMovie(Long movieId) {
        List<Showtime> showtimes = showtimeRepository.findByMovieId(movieId);
        return showtimes.stream()
                .map(showtimeMapper::toDTO)
                .collect(Collectors.toList());
    }
    public List<ShowtimeDTO> getAllShows() {
        List<Showtime> showtimes = showtimeRepository.findAll();
        return showtimes.stream()
                .map(showtimeMapper::toDTO)
                .collect(Collectors.toList());
    }


}
