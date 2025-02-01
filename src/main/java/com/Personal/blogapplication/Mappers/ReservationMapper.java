package com.Personal.blogapplication.Mappers;

import com.Personal.blogapplication.Dtos.ReservationDTO;
import com.Personal.blogapplication.Dtos.ShowtimeDTO;
import com.Personal.blogapplication.Entity.Reservation;
import com.Personal.blogapplication.Entity.Showtime;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReservationMapper {

    ReservationDTO toDTO(Reservation reservation);
    Reservation toEntity(ReservationDTO reservationDTO);

}
