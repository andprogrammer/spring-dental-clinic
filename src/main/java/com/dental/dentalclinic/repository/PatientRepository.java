package com.dental.dentalclinic.repository;

import com.dental.dentalclinic.model.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    // Derived query: get all active patients
    List<Patient> findByActiveTrue();

    // Derived query: find by last name ignoring case
    List<Patient> findByLastNameIgnoreCase(String lastName);

    // Derived query: find patients who haven't visited before a certain date
    List<Patient> findByLastVisitDateBefore(LocalDate date);

    // JPQL query with named parameter
    @Query("""
                select p from Patient p
                where p.active = true
                  and p.lastVisitDate <= :cutoff
            """)
    List<Patient> findPatientsRequiringVisit(@Param("cutoff") LocalDate cutoff);

    // Pagination example
    Page<Patient> findAllByActiveTrue(Pageable pageable);

    // Soft delete
    @org.springframework.data.jpa.repository.Modifying
    @org.springframework.data.jpa.repository.Query("update Patient p set p.active = false where p.id = :id")
    void deactivatePatient(@Param("id") Long id);
}
