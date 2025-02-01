package com.Personal.blogapplication.Controller;

import com.Personal.blogapplication.Dtos.ShowtimeDTO;
import com.Personal.blogapplication.Service.SaveShowtimeForMovie;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/showtimes")
@RequiredArgsConstructor
public class ShowtimeController {

    private final SaveShowtimeForMovie showtimeService;

    // Endpoint to save Showtime for a specific Movie
    @PostMapping("/movie/{movieId}")
    public ResponseEntity<ShowtimeDTO> saveShowtimeForMovie(
            @PathVariable Long movieId,
            @RequestBody  ShowtimeDTO showtimeDTO) {

        ShowtimeDTO savedShowtime = showtimeService.saveShowtimeForMovie(movieId, showtimeDTO);
        return new ResponseEntity<>(savedShowtime, HttpStatus.CREATED);
    }

    // Endpoint to update Showtime
    @PutMapping("/{showtimeId}")
    public ResponseEntity<ShowtimeDTO> updateShowtime(
            @PathVariable Long showtimeId,
            @RequestBody  ShowtimeDTO showtimeDTO) {

        ShowtimeDTO updatedShowtime = showtimeService.updateShowTime(showtimeId, showtimeDTO);
        return new ResponseEntity<>(updatedShowtime, HttpStatus.OK);
    }

    // Endpoint to get all showtimes for a movie
    @GetMapping("/movie/{movieId}")
    public ResponseEntity<List<ShowtimeDTO>> getShowtimesForMovie(@PathVariable Long movieId) {
        List<ShowtimeDTO> showtimes = showtimeService.getShowtimesForMovie(movieId);
        return new ResponseEntity<>(showtimes, HttpStatus.OK);
    }

    // Endpoint to get all showtimes
    @GetMapping
    public ResponseEntity<List<ShowtimeDTO>> getAllShowtimes() {
        List<ShowtimeDTO> showtimes = showtimeService.getAllShows();
        return new ResponseEntity<>(showtimes, HttpStatus.OK);
    }
}