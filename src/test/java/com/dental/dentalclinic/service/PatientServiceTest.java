package com.dental.dentalclinic.service;

import com.dental.dentalclinic.model.Patient;
import com.dental.dentalclinic.repository.PatientRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
class PatientServiceTest {

    @Mock
    private PatientRepository repository;

    @InjectMocks
    private PatientService service;

    @Test
    void shouldReturnPatientsRequiringVisit() {
        Patient patient1 = new Patient(
                "John", "Doe", LocalDate.now().minusMonths(7)
        );

        when(repository.findPatientsRequiringVisit(any(LocalDate.class)))
                .thenReturn(List.of(patient1));

        List<Patient> result = service.getPatientsRequiringVisit();

        assertThat(result).hasSize(1);
        assertThat(result.getFirst().getFirstName()).isEqualTo("John");
    }
}
