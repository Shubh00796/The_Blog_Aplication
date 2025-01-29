package com.Personal.blogapplication.Controller;

import com.Personal.blogapplication.Dtos.OnlineCourseDTO;
import com.Personal.blogapplication.Service.OnlineCourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/online-courses")
@RequiredArgsConstructor
public class OnlineCourseController {

    private final OnlineCourseService onlineCourseService;

    @PostMapping
    public ResponseEntity<OnlineCourseDTO> createCourse(@RequestBody OnlineCourseDTO onlineCourseDTO) {
        return new ResponseEntity<>(onlineCourseService.createCourse(onlineCourseDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OnlineCourseDTO> updateCourse(@PathVariable Long id, @RequestBody OnlineCourseDTO onlineCourseDTO) {
        return ResponseEntity.ok(onlineCourseService.updateCourse(id, onlineCourseDTO));
    }

    @GetMapping
    public ResponseEntity<List<OnlineCourseDTO>> getAllCourses() {
        return ResponseEntity.ok(onlineCourseService.getAllCourses());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        onlineCourseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }
}
