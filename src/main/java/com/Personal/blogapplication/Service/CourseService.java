package com.Personal.blogapplication.Service;

import com.Personal.blogapplication.Dtos.CourseDTO;
import com.Personal.blogapplication.Entity.Course;
import com.Personal.blogapplication.Entity.Student;
import com.Personal.blogapplication.Repo.CourseRepository;
import com.Personal.blogapplication.Utils.DTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private DTOMapper dtoMapper;

    public CourseDTO addCourse(CourseDTO courseDTO) {
        Course courseEntity = dtoMapper.toCourseEntity(courseDTO);
        Course savedCourse = courseRepository.save(courseEntity);
        return dtoMapper.toCourseDTO(savedCourse);
    }

    public List<CourseDTO> getAllCourses() {
        return courseRepository.findAll().stream()
                .map(dtoMapper::toCourseDTO)
                .collect(Collectors.toList());

    }

    public CourseDTO getCourseById(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Course not found"));
        return dtoMapper.toCourseDTO(course);
    }

    public CourseDTO updateCourse(Long id, CourseDTO courseDTO) {
        Course existingCourse = courseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Course not found"));
        existingCourse.setCourseName(courseDTO.getCourseName());
        existingCourse.setCourseCode(courseDTO.getCourseCode());
        existingCourse.setCredits(courseDTO.getCredits());
        return dtoMapper.toCourseDTO(courseRepository.save(existingCourse));
    }

    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }


}
