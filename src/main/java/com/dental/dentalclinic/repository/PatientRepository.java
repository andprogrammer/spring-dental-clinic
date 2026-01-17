package com.dental.dentalclinic.repository;

import com.dental.dentalclinic.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    @Query("""
        SELECT p FROM Patient p
        WHERE p.lastVisitDate <= :cutoffDate
    """)
    List<Patient> findPatientsRequiringVisit(LocalDate cutoffDate);
}
