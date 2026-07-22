package com.sirajchaudhary.healthcare.patient.response;

import io.micronaut.serde.annotation.Serdeable;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Serdeable
public class PatientResponse {

    private Long id;

    private String patientCode;

    private String firstName;

    private String lastName;

    private LocalDate dateOfBirth;

    private String gender;

    private String bloodGroup;

    private String email;

    private String phoneNumber;

    private String addressLine1;

    private String addressLine2;

    private String city;

    private String state;

    private String country;

    private String postalCode;

    private String hospitalCode;

    private String hospitalName;

    private Boolean active;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}