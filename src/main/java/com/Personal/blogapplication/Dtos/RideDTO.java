package com.Personal.blogapplication.Dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.Coordinate;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RideDTO {
    private String riderName;
    private String pickupLocation;
    private String dropOffLocation;
    private Coordinate pickupCoordinates;
    private Coordinate dropOffCoordinates;
    private BigDecimal fare;
    private String status;
}
