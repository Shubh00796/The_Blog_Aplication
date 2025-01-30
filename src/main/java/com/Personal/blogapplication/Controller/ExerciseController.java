package com.Personal.blogapplication.Controller;

import com.Personal.blogapplication.Dtos.ExerciseDTO;
import com.Personal.blogapplication.Service.ExerciseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exercises")
@RequiredArgsConstructor
public class ExerciseController {

    private final ExerciseService exerciseService;

    @PostMapping
    public ResponseEntity<ExerciseDTO> createExercise(@RequestBody ExerciseDTO exerciseDTO) {
        try {
            ExerciseDTO createdExercise = exerciseService.createExercise(exerciseDTO);
            return new ResponseEntity<>(createdExercise, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    // Endpoint to update an exercise
    @PutMapping("/{username}/{exerciseId}")
    public ResponseEntity<List<ExerciseDTO>> updateExercise(
            @PathVariable String username,
            @PathVariable Long exerciseId,
            @RequestBody ExerciseDTO exerciseDTO) {
        try {
            List<ExerciseDTO> updatedExercise = exerciseService.updateExercise(username, exerciseDTO, exerciseId);
            return new ResponseEntity<>(updatedExercise, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<ExerciseDTO>> getAllExercises() {
        List<ExerciseDTO> exercises = exerciseService.getAllExercies();
        return new ResponseEntity<>(exercises, HttpStatus.OK);
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<List<ExerciseDTO>> getExercisesByUsername(@PathVariable String username) {
        List<ExerciseDTO> exercises = exerciseService.getExerciesByUsername(username);
        if (exercises.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(exercises, HttpStatus.OK);
    }
}