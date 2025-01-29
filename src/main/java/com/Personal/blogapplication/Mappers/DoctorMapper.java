package com.Personal.blogapplication.Mappers;

import com.Personal.blogapplication.Dtos.DoctorDTO;
import com.Personal.blogapplication.Dtos.PatientDTO;
import com.Personal.blogapplication.Entity.Doctor;
import com.Personal.blogapplication.Entity.Patient;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DoctorMapper {
    Doctor toEntity(DoctorDTO doctorDTO);
    DoctorDTO toDTO(Doctor doctor);
}
