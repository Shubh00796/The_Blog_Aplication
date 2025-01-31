package com.Personal.blogapplication.Entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Data
@Table(name = "seats")
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "row_num")
    private int rowNumber;  // Renamed from 'row_number' to 'rowNumber'

    @Column(name = "col_num")
    private int columnNumber;

    @Column(name = "is_booked")
    private boolean booked;

    private String username;

    @Column(nullable = false, unique = true)
    private String seatNumber;

    @Column(nullable = false)
    private double price;
}
