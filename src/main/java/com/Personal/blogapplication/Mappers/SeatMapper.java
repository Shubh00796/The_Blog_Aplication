package com.Personal.blogapplication.Mappers;

import com.Personal.blogapplication.Dtos.SeatDTO;
import com.Personal.blogapplication.Entity.Seat;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SeatMapper {

    SeatDTO toDTO(Seat seat);

    Seat toEntity(SeatDTO seatDTO);
}