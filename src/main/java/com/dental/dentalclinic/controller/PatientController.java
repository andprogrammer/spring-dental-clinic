package com.dental.dentalclinic.controller;

import com.dental.dentalclinic.model.Patient;
import com.dental.dentalclinic.service.PatientService;
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
    public List<Patient> patientsRequiringVisit() {
        return service.getPatientsRequiringVisit();
    }
}

