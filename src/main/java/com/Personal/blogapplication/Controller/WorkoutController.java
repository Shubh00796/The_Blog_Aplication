package com.Personal.blogapplication.Controller;

import com.Personal.blogapplication.Dtos.WorkoutDTO;
import com.Personal.blogapplication.Service.WorkoutService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workouts")
@RequiredArgsConstructor
@Slf4j
public class WorkoutController {

    private final WorkoutService workoutService;

    // Create a new workout (requires authentication)
    @PostMapping
    public ResponseEntity<WorkoutDTO> createWorkout(@RequestBody @Valid WorkoutDTO workoutDTO) {
        log.info("Creating new workout: {}", workoutDTO);
        WorkoutDTO createdWorkout = workoutService.createWorkout(workoutDTO);
        return new ResponseEntity<>(createdWorkout, HttpStatus.CREATED);
    }

    // Update workouts by username (requires authentication)
    @PutMapping("/update/{username}/{workoutId}")
    public ResponseEntity<List<WorkoutDTO>> updateWorkout(
            @PathVariable String username,
            @RequestBody @Valid WorkoutDTO updatedDto,
            @PathVariable Long workoutId) {
        log.info("Updating workout for username: {} with workoutId: {}", username, workoutId);
        try {
            List<WorkoutDTO> updatedWorkouts = workoutService.updateWorkoutsByUsername(username, updatedDto, workoutId);
            return new ResponseEntity<>(updatedWorkouts, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            log.error("Workout not found: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Get all workouts
    @GetMapping
    public ResponseEntity<List<WorkoutDTO>> getAllWorkouts() {
        log.info("Fetching all workouts");
        List<WorkoutDTO> workouts = workoutService.getAllWorkouts();
        return new ResponseEntity<>(workouts, HttpStatus.OK);
    }

    // Get workouts by username
    @GetMapping("/username/{username}")
    public ResponseEntity<List<WorkoutDTO>> getWorkoutsByUsername(@PathVariable String username) {
        log.info("Fetching workouts for username: {}", username);
        List<WorkoutDTO> workouts = workoutService.getWorkoutByUsername(username);
        if (workouts.isEmpty()) {
            log.warn("No workouts found for username: {}", username);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);  // Return 204 if no workouts found
        }
        return new ResponseEntity<>(workouts, HttpStatus.OK);
    }
}
