package com.sirajchaudhary.healthcare.hospital.request;

import io.micronaut.serde.annotation.Serdeable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Serdeable
public class UpdateHospitalRequest {

    @NotBlank(message = "Hospital name is required.")
    private String hospitalName;

    @NotBlank(message = "Registration number is required.")
    private String registrationNumber;

    @NotBlank(message = "Email is required.")
    @Email(message = "Invalid email address.")
    private String email;

    @NotBlank(message = "Phone number is required.")
    private String phoneNumber;

    private String website;

    @NotBlank(message = "Address Line 1 is required.")
    private String addressLine1;

    private String addressLine2;

    @NotBlank(message = "City is required.")
    private String city;

    @NotBlank(message = "State is required.")
    private String state;

    @NotBlank(message = "Country is required.")
    private String country;

    @NotBlank(message = "Postal code is required.")
    private String postalCode;
}