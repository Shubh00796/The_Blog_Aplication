package com.Personal.blogapplication.Service;

import com.Personal.blogapplication.Dtos.StudentDTO;
import com.Personal.blogapplication.Entity.Student;
import com.Personal.blogapplication.Repo.StudentRepository;
import com.Personal.blogapplication.Utils.DTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private DTOMapper dtoMapper;

    public StudentDTO addStudent(StudentDTO studentDTO) {
        Student studentEntity = dtoMapper.toStudentEntity(studentDTO);
        Student savedStudent = studentRepository.save(studentEntity);
        return dtoMapper.toStudentDTO(savedStudent);
    }

    public List<StudentDTO> getAllStudents() {
        return studentRepository.findAll().stream()
                .map(dtoMapper::toStudentDTO)
                .collect(Collectors.toList());
    }

    public StudentDTO getStudentById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Student not found"));
        return dtoMapper.toStudentDTO(student);
    }

    public StudentDTO updateStudent(Long id, StudentDTO studentDTO) {
        Student existingStudent = studentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Student not found"));
        existingStudent.setName(studentDTO.getName());
        existingStudent.setEmail(studentDTO.getEmail());
        existingStudent.setDateOfBirth(studentDTO.getDateOfBirth());
        Student updatedStudent = studentRepository.save(existingStudent);
        return dtoMapper.toStudentDTO(updatedStudent);

    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}
