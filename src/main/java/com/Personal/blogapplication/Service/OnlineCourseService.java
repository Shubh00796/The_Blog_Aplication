package com.Personal.blogapplication.Service;

import com.Personal.blogapplication.Dtos.OnlineCourseDTO;
import com.Personal.blogapplication.Entity.OnlineCourse;
import com.Personal.blogapplication.Mappers.OnlineCourseMapper;
import com.Personal.blogapplication.Repo.OnlineCourseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class OnlineCourseService {

    private final OnlineCourseRepository onlineCourseRepository;
    private final OnlineCourseMapper onlineCourseMapper;

    public OnlineCourseDTO createCourse(OnlineCourseDTO onlineCourseDTO) {
        OnlineCourse entity = onlineCourseMapper.toEntity(onlineCourseDTO);
        OnlineCourse savedCourse = onlineCourseRepository.save(entity);
        return onlineCourseMapper.toDTO(savedCourse);
    }

    public OnlineCourseDTO updateCourse(Long id, OnlineCourseDTO onlineCourseDTO) {
        OnlineCourse course = onlineCourseRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course ID not found"));

        if (onlineCourseDTO.getTitle() != null) {
            course.setTitle(onlineCourseDTO.getTitle());
        }
        if (onlineCourseDTO.getDescription() != null) {
            course.setDescription(onlineCourseDTO.getDescription());
        }
        if (onlineCourseDTO.getInstructorName() != null) {
            course.setInstructorName(onlineCourseDTO.getInstructorName());
        }

        OnlineCourse updatedCourse = onlineCourseRepository.save(course);
        return onlineCourseMapper.toDTO(updatedCourse);
    }

    public List<OnlineCourseDTO> getAllCourses() {
        return onlineCourseRepository.findAll().stream()
                .map(onlineCourseMapper::toDTO)
                .collect(Collectors.toList());
    }

    public void deleteCourse(Long id) {
        if (!onlineCourseRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Course ID not found");
        }
        onlineCourseRepository.deleteById(id);
    }
}
