package com.Personal.blogapplication.Service;

import com.Personal.blogapplication.Dtos.DoctorDTO;
import com.Personal.blogapplication.Dtos.PatientDTO;
import com.Personal.blogapplication.Entity.Doctor;
import com.Personal.blogapplication.Mappers.DoctorMapper;
import com.Personal.blogapplication.Repo.DoctorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private DoctorMapper doctorMapper;


    public DoctorDTO addDoctor(DoctorDTO doctorDTO) {
        Doctor entity = doctorMapper.toEntity(doctorDTO);
        Doctor savedEntity = doctorRepository.save(entity);
        return doctorMapper.toDTO(savedEntity);
    }

    public DoctorDTO updateDoctor(Long id, DoctorDTO doctorDTO) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Doctor not found"));

        if (doctorDTO.getName() != null) {
            doctor.setName(doctorDTO.getName());
        }
        if (doctorDTO.getContactInfo() != null) {
            doctor.setContactInfo(doctorDTO.getContactInfo());
        }
        if (doctorDTO.getSpecialization() != null) {
            doctor.setSpecialization(doctorDTO.getSpecialization());
        }

        Doctor updatedDoctor = doctorRepository.save(doctor);
        return doctorMapper.toDTO(updatedDoctor);
    }


    public List<DoctorDTO> getAllDoctors() {
        return doctorRepository.findAll().stream()
                .map(doctor -> doctorMapper.toDTO(doctor))
                .collect(Collectors.toList());
    }

    public List<DoctorDTO> getAll() {
        return doctorRepository.findAll().stream()
                .map(doctorMapper::toDTO)
                .collect(Collectors.toList());
    }

    public DoctorDTO getDoctorById(Long id) {
        Doctor doctorById = doctorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Doctor not found"));
        return doctorMapper.toDTO(doctorById);

    }

    public void deleteDoctor(Long id) {
        doctorRepository.deleteById(id);
    }
}
