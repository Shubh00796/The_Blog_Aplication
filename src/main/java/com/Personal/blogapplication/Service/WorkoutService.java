package com.Personal.blogapplication.Service;

import com.Personal.blogapplication.Dtos.WorkoutDTO;
import com.Personal.blogapplication.Entity.Workout;
import com.Personal.blogapplication.Mappers.WorkoutMapper;
import com.Personal.blogapplication.Repo.WorkoutRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class WorkoutService {
    private final WorkoutRepository workoutRepository;
    private final WorkoutMapper workoutMapper;

    public WorkoutDTO createWorkout(WorkoutDTO workoutDTO){
        Workout entity = workoutMapper.toEntity(workoutDTO);
        Workout savedWorkout = workoutRepository.save(entity);
        return workoutMapper.toDTO(savedWorkout);
    }

    public List<WorkoutDTO> updateWorkoutsByUsername(String username, WorkoutDTO updatedDto, Long workoutId) {
        List<Workout> existingWorkouts = workoutRepository.findByUsername(username);

        if (existingWorkouts.isEmpty()) {
            throw new RuntimeException("No workouts found for username: " + username);
        }

        if (workoutId != null) {
            Workout workoutToUpdate = existingWorkouts.stream()
                    .filter(workout -> workout.getId().equals(workoutId))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Workout not found for id: " + workoutId));

            updateWorkoutFields(workoutToUpdate, updatedDto);
            workoutRepository.save(workoutToUpdate);

            return List.of(workoutMapper.toDTO(workoutToUpdate));  // Return updated workout as DTO
        }

        existingWorkouts.forEach(workout -> updateWorkoutFields(workout, updatedDto));

        workoutRepository.saveAll(existingWorkouts);

        return existingWorkouts.stream()
                .map(workoutMapper::toDTO)
                .toList();
    }

    private void updateWorkoutFields(Workout workout, WorkoutDTO updatedDto) {
        if (updatedDto.getName() != null) {
            workout.setName(updatedDto.getName());
        }
        if (updatedDto.getScheduledTime() != null) {
            workout.setScheduledTime(updatedDto.getScheduledTime());
        }
    }

    public List<WorkoutDTO>  getAllWorkouts() {
       return workoutRepository.findAll().stream()
                .map(workoutMapper::toDTO)
                .collect(Collectors.toList());
    }
    public List<WorkoutDTO> getWorkoutByUsername(String username) {
        return workoutRepository.findByUsername(username).stream()
                .map(workoutMapper::toDTO)
                .collect(Collectors.toList());
    }
}
