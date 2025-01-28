package com.Personal.blogapplication.Mappers;

import com.Personal.blogapplication.Dtos.PatientDTO;
import com.Personal.blogapplication.Entity.Patient;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PatientMapper {
    Patient toEntity(PatientDTO patientDTO);
    PatientDTO toDTO(Patient patient);
}
