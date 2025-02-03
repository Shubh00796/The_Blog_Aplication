package com.Personal.blogapplication.Mappers;

import com.Personal.blogapplication.Dtos.RideDTO;
import com.Personal.blogapplication.Entity.Ride;
import com.Personal.blogapplication.Utils.RideMapperHelper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = RideMapperHelper.class)  // Correctly using the helper class
public interface RideMapper {

    // Mapping method to convert Ride entity to RideDTO
    RideDTO rideToRideDTO(Ride ride);

    // Mapping method to convert RideDTO to Ride entity
    @Mapping(target = "pickupCoordinates", qualifiedByName = "coordinateToPoint")  // Using qualifiedByName to use helper method
    @Mapping(target = "dropOffCoordinates", qualifiedByName = "coordinateToPoint")  // Using qualifiedByName to use helper method
    Ride rideDTOToRide(RideDTO rideDTO);
}
