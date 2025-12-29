package com.dental.dentalclinic.service;

import com.dental.dentalclinic.model.Patient;
import com.dental.dentalclinic.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientService {

    private final PatientRepository repository;

    public PatientService(PatientRepository repository) {
        this.repository = repository;
    }

    public Patient addPatient(Patient patient) {
        return repository.save(patient);
    }

    public List<Patient> getAllPatients() {
        return repository.findAll();
    }

    public List<Patient> getPatientsRequiringVisit() {
        return repository.findAll()
                .stream()
                .filter(Patient::requiresVisit)
                .collect(Collectors.toList());
    }
}

