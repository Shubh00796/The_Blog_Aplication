package com.Personal.blogapplication.Mappers;

import com.Personal.blogapplication.Dtos.MovieDTO;
import com.Personal.blogapplication.Dtos.ShowtimeDTO;
import com.Personal.blogapplication.Entity.Movie;
import com.Personal.blogapplication.Entity.Showtime;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ShowtimeMapper {

    ShowtimeDTO toDTO(Showtime showtime);
    Showtime toEntity(ShowtimeDTO showtimeDTO);

}
