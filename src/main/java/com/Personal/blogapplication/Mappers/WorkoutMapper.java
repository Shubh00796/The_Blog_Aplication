package com.Personal.blogapplication.Mappers;


import com.Personal.blogapplication.Dtos.WorkoutDTO;
import com.Personal.blogapplication.Entity.Workout;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WorkoutMapper {
    Workout toEntity(WorkoutDTO workoutDTO);
    WorkoutDTO toDTO(Workout workout);
        // Important! Tells MapStruct this is an update method
}
