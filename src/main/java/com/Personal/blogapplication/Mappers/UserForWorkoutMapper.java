package com.Personal.blogapplication.Mapper;

import com.Personal.blogapplication.Dtos.AuthResponseDTO;
import com.Personal.blogapplication.Entity.UserForWorkout;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserForWorkoutMapper {


    AuthResponseDTO toAuthResponseDTO(UserForWorkout user, String token);
}
