package com.dental.dentalclinic.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private LocalDate lastVisitDate;

    protected Patient() {
    }

    public Patient(String firstName, String lastName, LocalDate lastVisitDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.lastVisitDate = lastVisitDate;
    }

    public boolean requiresVisit() {
        return !lastVisitDate.plusMonths(6).isAfter(LocalDate.now());
    }

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
}
