package com.Personal.blogapplication.Controller;

import com.Personal.blogapplication.Dtos.*;
import com.Personal.blogapplication.Service.TheatreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/theatre")
@RequiredArgsConstructor
public class TheatreController {

    private final TheatreService theatreService;

    @PostMapping("/initialize")
    public ResponseEntity<String> initializeSeats(@RequestParam int rows, @RequestParam int cols) {
        theatreService.initializeSeats(rows, cols);
        return ResponseEntity.ok("Theatre seats initialized successfully.");
    }

    @PostMapping("/book")
    public ResponseEntity<SeatDTO> bookSeat(@RequestBody BookingRequestDTO requestDTO) {
        return ResponseEntity.ok(theatreService.bookSeat(requestDTO));
    }

    @PostMapping("/cancel")
    public ResponseEntity<BookingResponseDTO> cancelBooking(@RequestBody CancelRequestDTO request) {
        return ResponseEntity.ok(theatreService.cancelBooking(request));
    }

    @PostMapping("/update")
    public ResponseEntity<SeatDTO> updateBooking(@RequestBody UpdateRequestDTO request) {
        return ResponseEntity.ok(theatreService.updateBooking(request));
    }

    @GetMapping("/revenue")
    public ResponseEntity<Double> getRevenue() {
        return ResponseEntity.ok(theatreService.calculateRevenue());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
