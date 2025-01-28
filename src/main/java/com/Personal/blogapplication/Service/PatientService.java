package com.Personal.blogapplication.Service;

import com.Personal.blogapplication.Dtos.PatientDTO;
import com.Personal.blogapplication.Entity.Patient;
import com.Personal.blogapplication.Mappers.PatientMapper;
import com.Personal.blogapplication.Repo.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatientService {
    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;

    public PatientDTO addPatient(PatientDTO patientDTO) {
        Patient entity = patientMapper.toEntity(patientDTO);
        Patient savedPatient = patientRepository.save(entity);
        return patientMapper.toDTO(savedPatient);
    }

    public PatientDTO updatePatient(Long id, PatientDTO patientDTO) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Patient not found"));

        patient.setName(patientDTO.getName());
        patient.setAge(patientDTO.getAge());
        patient.setGender(patientDTO.getGender());
        patient.setContactInfo(patientDTO.getContactInfo());
        Patient savedUpdatedPatient = patientRepository.save(patient);
        return patientMapper.toDTO(savedUpdatedPatient);
    }

    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }

    public PatientDTO getPatientById(Long id) {
        Patient patient = patientRepository.findById(id).orElseThrow(() -> new RuntimeException("Patient not found"));
        return patientMapper.toDTO(patient);
    }

    public List<PatientDTO> getAllPatients() {
        return patientRepository.findAll().stream().map(patientMapper::toDTO).collect(Collectors.toList());
    }
}
