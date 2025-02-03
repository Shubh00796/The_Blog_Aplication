package com.Personal.blogapplication.Service;

import com.Personal.blogapplication.Dtos.RideDTO;

public interface RideService {
    RideDTO requestRide(RideDTO rideDTO);
    RideDTO matchDriver(Long rideId);
    RideDTO updateRideStatus(Long rideId, String status);
    void cancelRide(Long rideId);
    RideDTO simulatePayment(Long rideId);
}