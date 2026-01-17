package com.dental.dentalclinic.service;

import com.dental.dentalclinic.model.Patient;
import com.dental.dentalclinic.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

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
        LocalDate cutoff = LocalDate.now().minusMonths(6);
        return repository.findPatientsRequiringVisit(cutoff);
    }
}
