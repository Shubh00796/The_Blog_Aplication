package com.Personal.blogapplication.Mappers;

import com.Personal.blogapplication.Dtos.MovieDTO;
import com.Personal.blogapplication.Entity.Movie;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MovieMapper {

    MovieDTO toDTO(Movie movie);
    Movie toEntity(MovieDTO movieDTO);

}
