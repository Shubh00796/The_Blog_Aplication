package com.Personal.blogapplication.Service;

import com.Personal.blogapplication.Dtos.*;
import com.Personal.blogapplication.Entity.Seat;
import com.Personal.blogapplication.Mappers.SeatMapper;
import com.Personal.blogapplication.Repo.SeatRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class TheatreService {

    private final SeatRepository seatRepository;
    private final SeatMapper seatMapper;

    public void initializeSeats(int rows, int cols) {
        char rowLabel = 'A';
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                String seatNumber = rowLabel + String.valueOf(j + 1);
                Seat seat = Seat.builder()
                        .rowNumber(i)
                        .columnNumber(j)
                        .booked(false)
                        .username(null)
                        .seatNumber(seatNumber)
                        .price(10.0)
                        .build();

                seatRepository.save(seat);
            }
            rowLabel++;
        }
    }

    public SeatDTO bookSeat(BookingRequestDTO requestDTO) {
        Seat seat = seatRepository.findBySeatNumber(requestDTO.getSeatNumber())
                .orElseThrow(() -> new RuntimeException("Seat " + requestDTO.getSeatNumber() + " not found"));

        if (seat.isBooked()) {
            throw new RuntimeException("Seat " + requestDTO.getSeatNumber() + " is already booked");
        }

        seat.setBooked(true);
        seat.setUsername(requestDTO.getUsername());

        Seat savedSeat = seatRepository.save(seat);
        return seatMapper.toDTO(savedSeat);
    }


    public BookingResponseDTO cancelBooking(CancelRequestDTO request) {
        Seat seat = seatRepository.findBySeatNumber(request.getSeatNumber())
                .filter(s -> s.isBooked() && s.getUsername().equals(request.getUsername()))
                .orElseThrow(() -> new RuntimeException("Seat not found or not booked by you"));

        seat.setBooked(false);
        seat.setUsername(null);
        seatRepository.save(seat);

        return BookingResponseDTO.builder()
                .message("Booking cancelled for " + request.getSeatNumber())
                .seat(seatMapper.toDTO(seat))
                .build();
    }

    public SeatDTO updateBooking(UpdateRequestDTO request) {
        cancelBooking(new CancelRequestDTO(request.getUsername(), request.getOldSeatNumber()));
        return bookSeat(new BookingRequestDTO(request.getUsername(), request.getNewSeatNumber()));
    }

    public double calculateRevenue() {
        return seatRepository.findAll().stream()
                .filter(Seat::isBooked)
                .mapToDouble(Seat::getPrice)
                .sum();
    }
}
