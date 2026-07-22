package com.sirajchaudhary.healthcare.patient.request;

import io.micronaut.serde.annotation.Serdeable;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Serdeable
public class UpdatePatientRequest {

    @NotBlank(message = "First name is required.")
    @Size(max = 100, message = "First name must not exceed 100 characters.")
    private String firstName;

    @NotBlank(message = "Last name is required.")
    @Size(max = 100, message = "Last name must not exceed 100 characters.")
    private String lastName;

    @NotNull(message = "Date of birth is required.")
    @Past(message = "Date of birth must be in the past.")
    private LocalDate dateOfBirth;

    @NotBlank(message = "Gender is required.")
    @Size(max = 20, message = "Gender must not exceed 20 characters.")
    private String gender;

    @Size(max = 10, message = "Blood group must not exceed 10 characters.")
    private String bloodGroup;

    @NotBlank(message = "Email is required.")
    @Email(message = "Invalid email format.")
    @Size(max = 150, message = "Email must not exceed 150 characters.")
    private String email;

    @NotBlank(message = "Phone number is required.")
    @Size(max = 20, message = "Phone number must not exceed 20 characters.")
    private String phoneNumber;

    @NotBlank(message = "Address Line 1 is required.")
    @Size(max = 255, message = "Address Line 1 must not exceed 255 characters.")
    private String addressLine1;

    @Size(max = 255, message = "Address Line 2 must not exceed 255 characters.")
    private String addressLine2;

    @NotBlank(message = "City is required.")
    @Size(max = 100, message = "City must not exceed 100 characters.")
    private String city;

    @NotBlank(message = "State is required.")
    @Size(max = 100, message = "State must not exceed 100 characters.")
    private String state;

    @NotBlank(message = "Country is required.")
    @Size(max = 100, message = "Country must not exceed 100 characters.")
    private String country;

    @NotBlank(message = "Postal code is required.")
    @Size(max = 20, message = "Postal code must not exceed 20 characters.")
    private String postalCode;

    @NotBlank(message = "Hospital code is required.")
    @Size(max = 20, message = "Hospital code must not exceed 20 characters.")
    private String hospitalCode;
}