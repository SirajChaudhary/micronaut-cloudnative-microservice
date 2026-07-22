package com.sirajchaudhary.healthcare.hospital.response;

import io.micronaut.serde.annotation.Serdeable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Serdeable
public class HospitalResponse {

    private Long id;

    private String hospitalCode;

    private String hospitalName;

    private String registrationNumber;

    private String email;

    private String phoneNumber;

    private String website;

    private String addressLine1;

    private String addressLine2;

    private String city;

    private String state;

    private String country;

    private String postalCode;

    private Boolean active;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}