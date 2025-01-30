package com.Personal.blogapplication.Service;

import com.Personal.blogapplication.Dtos.ExerciseDTO;
import com.Personal.blogapplication.Entity.Exercise;
import com.Personal.blogapplication.Entity.UserForWorkout;
import com.Personal.blogapplication.Mappers.ExerciseMapper;
import com.Personal.blogapplication.Repo.ExerciseRepository;
import com.Personal.blogapplication.Repo.UserForWorkoutReposiotry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ExerciseService {
    private final ExerciseRepository exerciseRepository;
    private final ExerciseMapper exerciseMapper;
    private final UserForWorkoutReposiotry userForWorkoutReposiotry;

    public ExerciseDTO createExercise(ExerciseDTO exerciseDTO) {
        Optional<UserForWorkout> username = userForWorkoutReposiotry.findByUsername(exerciseDTO.getUsername());
        if (!username.isPresent()) {
            throw new RuntimeException("User with username " + exerciseDTO.getUsername() + " does not exist.");

        }
        Exercise entity = exerciseMapper.toEntity(exerciseDTO);
        Exercise savedExercise = exerciseRepository.save(entity);
        return exerciseMapper.toDTO(savedExercise);
    }
    public List<ExerciseDTO> updateExercise(String username, ExerciseDTO exerciseDTO,Long exerciseId){
        List<Exercise> existingExercies = exerciseRepository.findByUsername(username);

        if(existingExercies.isEmpty()){
            throw new RuntimeException("No workouts found for username: " + username);

        }
        if(exerciseId != null){
            Exercise exerciseToUpdate = existingExercies.stream()
                    .filter(exercise -> exercise.getId().equals(exerciseId))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Workout not found for id: " + exerciseId));

            upadteExerciseFields(exerciseToUpdate,exerciseDTO);
            Exercise savedExercies = exerciseRepository.save(exerciseToUpdate);

            return List.of(exerciseMapper.toDTO(savedExercies));
        }
        throw new RuntimeException("Exercise ID must be provided for update");

    }


    private void upadteExerciseFields(Exercise exercise,ExerciseDTO upadtedDto){
        if(upadtedDto.getName() != null){
            exercise.setName(upadtedDto.getName());
        }
        if(upadtedDto.getCategory() != null){
            exercise.setCategory(upadtedDto.getCategory());
        }
        if(upadtedDto.getDescription() != null){
            exercise.setDescription(upadtedDto.getDescription());
        }
    }

    public List<ExerciseDTO> getAllExercies() {
        return exerciseRepository.findAll().stream()
                .map(exerciseMapper::toDTO)
                .collect(Collectors.toList());
    }
    public List<ExerciseDTO> getExerciesByUsername(String username){
        return  exerciseRepository.findByUsername(username).stream()
                .map(exerciseMapper::toDTO)
                .collect(Collectors.toList());
    }

}
