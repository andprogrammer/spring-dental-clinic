package com.dental.dentalclinic.repository;

import com.dental.dentalclinic.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
