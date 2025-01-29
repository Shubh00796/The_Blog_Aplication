package com.Personal.blogapplication.Mappers;

import com.Personal.blogapplication.Dtos.AppointmentDTO;
import com.Personal.blogapplication.Dtos.DoctorDTO;
import com.Personal.blogapplication.Entity.Appointment;
import com.Personal.blogapplication.Entity.Doctor;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AppointmentMapper {
    Appointment toEntity(AppointmentDTO appointmentDTO);
    AppointmentDTO toDTO(Appointment appointment);
}
