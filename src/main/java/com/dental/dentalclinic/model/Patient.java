package com.dental.dentalclinic.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(
        name = "patient",
        indexes = {
                @Index(name = "idx_patient_last_visit_date", columnList = "lastVisitDate"),
                @Index(name = "idx_patient_last_name", columnList = "lastName")
        }
)
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String firstName;

    @Column(nullable = false, length = 50)
    private String lastName;

    @Column(nullable = false)
    private LocalDate lastVisitDate;

    @Column(nullable = false)
    private boolean active = true;

    protected Patient() {
    }

    public Patient(String firstName, String lastName, LocalDate lastVisitDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.lastVisitDate = lastVisitDate;
        this.active = true;
    }

    /**
     * Domain logic: check if patient requires a visit.
     * This method is NOT used by JPA directly.
     */
    public boolean requiresVisit(LocalDate referenceDate) {
        return lastVisitDate
                .plusMonths(6)
                .isBefore(referenceDate.plusDays(1));
    }

    /**
     * Lifecycle callback: set default visit date if missing.
     */
    @PrePersist
    private void onCreate() {
        if (lastVisitDate == null) {
            lastVisitDate = LocalDate.now();
        }
    }

    // ---------- Getters ----------

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getLastVisitDate() {
        return lastVisitDate;
    }

    public boolean isActive() {
        return active;
    }

    // ---------- Setters (required for Hibernate dirty checking) ----------

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setLastVisitDate(LocalDate lastVisitDate) {
        this.lastVisitDate = lastVisitDate;
    }

    public void deactivate() {
        this.active = false;
    }
}
