package com.Personal.blogapplication.Mappers;


import com.Personal.blogapplication.Dtos.ExerciseDTO;
import com.Personal.blogapplication.Dtos.WorkoutDTO;
import com.Personal.blogapplication.Entity.Exercise;
import com.Personal.blogapplication.Entity.Workout;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExerciseMapper {
    Exercise toEntity(ExerciseDTO exerciseDTO);
    ExerciseDTO toDTO(Exercise exercise);
        // Important! Tells MapStruct this is an update method
}
