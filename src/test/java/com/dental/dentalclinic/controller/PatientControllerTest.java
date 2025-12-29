package com.dental.dentalclinic.controller;

import com.dental.dentalclinic.model.Patient;
import com.dental.dentalclinic.service.PatientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.beans.factory.annotation.Autowired;

@WebMvcTest(PatientController.class)
class PatientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PatientService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldReturnPatientsRequiringVisit() throws Exception {
        Patient patient = new Patient(
                "John", "Doe", LocalDate.now().minusMonths(7)
        );

        when(service.getPatientsRequiringVisit()).thenReturn(List.of(patient));

        mockMvc.perform(get("/patients/requires-visit"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName").value("John"));
    }

    @Test
    void shouldAddPatient() throws Exception {
        Patient patient = new Patient(
                "David", "Smith", LocalDate.now()
        );

        when(service.addPatient(any(Patient.class)))
                .thenReturn(patient);

        mockMvc.perform(post("/patients")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(patient)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("David"));
    }
}
