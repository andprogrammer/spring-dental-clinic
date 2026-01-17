package com.dental.dentalclinic.controller;

import com.dental.dentalclinic.model.Patient;
import com.dental.dentalclinic.service.PatientService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {

    private final PatientService service;

    public PatientController(PatientService service) {
        this.service = service;
    }

    @PostMapping
    public Patient addPatient(@RequestBody Patient patient) {
        return service.addPatient(patient);
    }

    @GetMapping
    public List<Patient> getAllPatients() {
        return service.getAllPatients();
    }

    @GetMapping("/requires-visit")
    public List<Patient> getPatientsRequiringVisit() {
        return service.getPatientsRequiringVisit();
    }

    @GetMapping("/active")
    public Page<Patient> getActivePatients(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return service.getActivePatients(page, size);
    }

    @PutMapping("/{id}/visited")
    public void markVisited(@PathVariable Long id) {
        service.markPatientVisited(id);
    }

    @DeleteMapping("/{id}")
    public void deactivatePatient(@PathVariable Long id) {
        service.deactivate(id);
    }
}
