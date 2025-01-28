package com.Personal.blogapplication.Factory;

import com.Personal.blogapplication.Entity.Enrollment;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class EnrollementFactory {
    public Enrollment createEnrollement(Long studentId, Long courseId){
        return Enrollment.builder()
                .studentId(studentId)
                .courseId(courseId)
                .enrollmentDate(LocalDate.now())
                .build();
    }
}
