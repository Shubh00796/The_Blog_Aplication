package com.Personal.blogapplication.Service;

import com.Personal.blogapplication.Dtos.EnrollmentDTO;
import com.Personal.blogapplication.Entity.Enrollment;
import com.Personal.blogapplication.Factory.EnrollementFactory;
import com.Personal.blogapplication.Repo.EnrollmentRepository;
import com.Personal.blogapplication.Utils.DTOMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EnrollmentService {
    @Autowired
    private DTOMapper dtoMapper;

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private EnrollementFactory enrollementFactory;

    public EnrollmentDTO enrollStudent(Long studentId, Long courseId) {
        List<Enrollment> enrolledStudent = enrollmentRepository.findByStudentId(studentId);
        if (!enrolledStudent.isEmpty()) {
            throw new IllegalArgumentException("Student has alreday enrolled to Course");

        }

        Enrollment enrollmentOfStudent = enrollementFactory.createEnrollement(studentId, courseId);
        Enrollment savedEnrollement = enrollmentRepository.save(enrollmentOfStudent);

        return dtoMapper.toEnrollmentDTO(savedEnrollement);
    }

    public EnrollmentDTO unrollStudent(Long enrollmentId) {
        Enrollment enrolledStudentTrue = enrollmentRepository.findById(enrollmentId)
                .orElseThrow(() -> new IllegalArgumentException("Enrollement of Studnet is not found"));
        if(enrolledStudentTrue.getEnrollmentDate() != null){
            throw new IllegalArgumentException("Student is alreday unrolled");

        }
        enrolledStudentTrue.setEnrollmentDate(LocalDate.now());
        Enrollment savedStudent = enrollmentRepository.save(enrolledStudentTrue);
        return dtoMapper.toEnrollmentDTO(savedStudent);

    }
    public List<EnrollmentDTO> getEnrollmentsByStudentId(Long studentId) {
        return enrollmentRepository.findByStudentId(studentId).stream()
                .map(dtoMapper::toEnrollmentDTO)
                .collect(Collectors.toList());
    }

    public List<EnrollmentDTO> getEnrollmentsByCourseId(Long courseId) {
        return enrollmentRepository.findByCourseId(courseId).stream()
                .map(dtoMapper::toEnrollmentDTO)
                .collect(Collectors.toList());
    }

    public void deleteEnrollment(Long id) {
        enrollmentRepository.deleteById(id);
    }

}
