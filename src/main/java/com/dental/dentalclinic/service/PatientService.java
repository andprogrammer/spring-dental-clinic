package com.dental.dentalclinic.service;

import com.dental.dentalclinic.model.Patient;
import com.dental.dentalclinic.repository.PatientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public Page<Patient> getActivePatients(int page, int size) {
        return repository.findAllByActiveTrue(
                PageRequest.of(page, size, Sort.by("lastName").ascending())
        );
    }

    // Mark patient as visited (transaction example)
    @Transactional
    public void markPatientVisited(Long patientId) {
        Patient patient = repository.findById(patientId)
                .orElseThrow(() -> new IllegalArgumentException("Patient not found"));
        patient.setLastVisitDate(LocalDate.now());
    }

    // Soft delete
    @Transactional
    public void deactivate(Long patientId) {
        repository.deactivatePatient(patientId);
    }
}
