package com.example.dentalclinic.model;

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

    public Patient() {}

    public Patient(String firstName, String lastName, LocalDate lastVisitDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.lastVisitDate = lastVisitDate;
    }

    public boolean requiresVisit() {
        return lastVisitDate.plusMonths(6).isBefore(LocalDate.now())
                || lastVisitDate.plusMonths(6).isEqual(LocalDate.now());
    }

    // Getters & setters
    public Long getId() { return id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public LocalDate getLastVisitDate() { return lastVisitDate; }
    public void setLastVisitDate(LocalDate lastVisitDate) {
        this.lastVisitDate = lastVisitDate;
    }
}
