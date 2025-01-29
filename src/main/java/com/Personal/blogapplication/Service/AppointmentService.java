package com.Personal.blogapplication.Service;

import com.Personal.blogapplication.Dtos.AppointmentDTO;
import com.Personal.blogapplication.Entity.Appointment;
import com.Personal.blogapplication.Entity.Doctor;
import com.Personal.blogapplication.Entity.Patient;
import com.Personal.blogapplication.Mappers.AppointmentMapper;
import com.Personal.blogapplication.Repo.AppointmentRepository;
import com.Personal.blogapplication.Repo.DoctorRepository;
import com.Personal.blogapplication.Repo.PatientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class AppointmentService {
    private final AppointmentRepository  appointmentRepository;
    private final AppointmentMapper appointmentMapper;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    public AppointmentDTO scheduleAppointment(AppointmentDTO appointmentDTO){
        Patient patient = patientRepository.findById(appointmentDTO.getPatientId())
                .orElseThrow(() -> new IllegalArgumentException("Patient with given id not found"));
        Doctor doctor = doctorRepository.findById(appointmentDTO.getDoctorId())
                .orElseThrow(()-> new IllegalArgumentException("Doctor with given id not found"));

        boolean  isDrAvalible = appointmentRepository.findByDoctorIdAndAppointmentTimeBetween(
                doctor.getId(),
                appointmentDTO.getAppointmentTime().minusMinutes(30),
                appointmentDTO.getAppointmentTime().plusMinutes(30)
        ).isEmpty();

        if(!isDrAvalible){
            throw new IllegalArgumentException("Doctor is not available at this time");

        }
        Appointment entity = appointmentMapper.toEntity(appointmentDTO);
        entity.setStatus("Scheduled");
        Appointment savedEntity = appointmentRepository.save(entity);
        return appointmentMapper.toDTO(savedEntity);
    }

    public AppointmentDTO rescheduleAppointment(Long appointmentId, LocalDateTime newTime){
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new IllegalArgumentException(" Appointment not found"));

        boolean isDoctorAvailable = appointmentRepository.findByDoctorIdAndAppointmentTimeBetween(
                appointment.getDoctorId(),
                newTime.minusMinutes(30),
                newTime.plusMinutes(30)
        ).isEmpty();

        if (!isDoctorAvailable) {
            throw new IllegalArgumentException("Doctor is not available at the new time");
        }

        appointment.setAppointmentTime(newTime);
        Appointment updatedAppointment = appointmentRepository.save(appointment);
        return appointmentMapper.toDTO(updatedAppointment);
    }

    public void cancelAppointment(Long appointmentId){
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new IllegalArgumentException("Appoitment for given iod is not found"));
        appointment.setStatus("Canceled");
        appointmentRepository.save(appointment);
    }
    public List<AppointmentDTO> getAllAppointments() {
        return appointmentRepository.findAll().stream()
                .map(appointmentMapper::toDTO)
                .collect(Collectors.toList());
    }
    public List<AppointmentDTO> getAppointmentsByDoctor(Long doctorId){
        return appointmentRepository.findByDoctorId(doctorId).stream()
                .map(appointmentMapper::toDTO)
                .collect(Collectors.toList());
    }
    public List<AppointmentDTO> getAppointmentsByPatient(Long patientId){
        return appointmentRepository.findByPatientId(patientId).stream()
                .map(appointmentMapper::toDTO)
                .collect(Collectors.toList());
    }


}
