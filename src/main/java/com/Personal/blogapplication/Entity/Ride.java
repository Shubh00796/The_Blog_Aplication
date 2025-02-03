package com.Personal.blogapplication.Entity;

import com.Personal.blogapplication.enums.RideStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.Point; // Make sure this is the only Point import
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ride {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String riderName;
    private String pickupLocation;
    private String dropOffLocation;

    @Column(columnDefinition = "geometry(Point, 4326)")
    private Point pickupCoordinates;

    @Column(columnDefinition = "geometry(Point, 4326)")
    private Point dropOffCoordinates;

    private BigDecimal fare;

    @Enumerated(EnumType.STRING)
    private RideStatus status;
}
