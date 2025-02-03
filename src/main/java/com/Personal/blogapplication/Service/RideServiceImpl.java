package com.Personal.blogapplication.Service;

import com.Personal.blogapplication.Dtos.RideDTO;
import com.Personal.blogapplication.Entity.Driver;
import com.Personal.blogapplication.Entity.Ride;
import com.Personal.blogapplication.Mappers.RideMapper;
import com.Personal.blogapplication.Repo.DriverRepository;
import com.Personal.blogapplication.Repo.RideRepository;
import com.Personal.blogapplication.Utils.RideMatchedEvent;
import com.Personal.blogapplication.enums.RideStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class RideServiceImpl implements RideService{
    private final RideRepository rideRepository;
    private final RideMapper rideMapper;
    private final DriverRepository driverRepository;
    private final ApplicationEventPublisher eventPublisher;
    private final GeometryFactory geometryFactory = new GeometryFactory();





    @Override
    public RideDTO requestRide(RideDTO rideDTO) {
        // Convert coordinates into JTS Point objects
        Point pickupPoint = geometryFactory.createPoint(
                new Coordinate(rideDTO.getPickupCoordinates().getX(), rideDTO.getPickupCoordinates().getY()));
        Point dropOffPoint = geometryFactory.createPoint(
                new Coordinate(rideDTO.getDropOffCoordinates().getX(), rideDTO.getDropOffCoordinates().getY()));

        // Map DTO to Entity
        Ride ride = rideMapper.rideDTOToRide(rideDTO);
        ride.setPickupCoordinates(pickupPoint);
        ride.setDropOffCoordinates(dropOffPoint);

        // Save ride
        Ride savedRide = rideRepository.save(ride);
        return rideMapper.rideToRideDTO(savedRide);
    }

    @Override
    public RideDTO matchDriver(Long rideId) {
        Ride ride = rideRepository.findById(rideId)
                .orElseThrow(() -> new IllegalArgumentException("Ride not found with ID: " + rideId));

        // Create Point object for pickup location
        Point pickupCoordinates = geometryFactory.createPoint(
                new Coordinate(ride.getPickupCoordinates().getX(), ride.getPickupCoordinates().getY()));

        double searchRadiusInMeters = 5000; // 5 km

        // Convert coordinates to WKT format ("POINT(lon lat)")
        String locationWKT = String.format("POINT(%f %f)", pickupCoordinates.getX(), pickupCoordinates.getY());

        List<Driver> nearbyDrivers = driverRepository.findByAvailableTrueAndCurrentLocationNear(locationWKT, searchRadiusInMeters);

        if (nearbyDrivers.isEmpty()) {
            throw new IllegalStateException("No available drivers found near the pickup location.");
        }

        // Assign the first available driver
        Driver matchedDriver = nearbyDrivers.get(0);
        matchedDriver.setAvailable(false);
        driverRepository.save(matchedDriver);

        // Update ride status
        ride.setStatus(RideStatus.MATCHED);
        Ride updatedRide = rideRepository.save(ride);

        // Publish event
        eventPublisher.publishEvent(new RideMatchedEvent(this, rideId));

        return rideMapper.rideToRideDTO(updatedRide);
    }

    @Override
    public RideDTO updateRideStatus(Long rideId, String status) {
        Ride ride = rideRepository.findById(rideId)
                .orElseThrow(() -> new RuntimeException("Ride not found with ID: " + rideId));
        ride.setStatus(RideStatus.valueOf(status));
        Ride updatedRide = rideRepository.save(ride);
        return rideMapper.rideToRideDTO(updatedRide);
    }

    @Override
    public void cancelRide(Long rideId) {
        Ride ride = rideRepository.findById(rideId)
                .orElseThrow(() -> new RuntimeException("Ride not found with ID: " + rideId));
        ride.setStatus(RideStatus.CANCELLED);
        rideRepository.save(ride);
    }

    @Override
    public RideDTO simulatePayment(Long rideId) {
        Ride ride = rideRepository.findById(rideId)
                .orElseThrow(() -> new RuntimeException("Ride not found with ID: " + rideId));
        ride.setStatus(RideStatus.COMPLETED);
        Ride updatedRide = rideRepository.save(ride);
        return rideMapper.rideToRideDTO(updatedRide);
    }
}
