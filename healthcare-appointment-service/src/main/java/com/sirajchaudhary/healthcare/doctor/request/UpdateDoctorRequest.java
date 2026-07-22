package com.sirajchaudhary.healthcare.doctor.request;

import io.micronaut.serde.annotation.Serdeable;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Serdeable
public class UpdateDoctorRequest {

    @NotBlank(message = "First name is required.")
    @Size(max = 100, message = "First name must not exceed 100 characters.")
    private String firstName;

    @NotBlank(message = "Last name is required.")
    @Size(max = 100, message = "Last name must not exceed 100 characters.")
    private String lastName;

    @NotBlank(message = "Specialization is required.")
    @Size(max = 100, message = "Specialization must not exceed 100 characters.")
    private String specialization;

    @Size(max = 150, message = "Qualification must not exceed 150 characters.")
    private String qualification;

    @NotNull(message = "Experience is required.")
    @Min(value = 0, message = "Experience cannot be negative.")
    private Integer experience;

    @NotNull(message = "Consultation fee is required.")
    @DecimalMin(value = "0.0", inclusive = false, message = "Consultation fee must be greater than 0.")
    private BigDecimal consultationFee;

    @NotBlank(message = "Email is required.")
    @Email(message = "Invalid email format.")
    @Size(max = 150, message = "Email must not exceed 150 characters.")
    private String email;

    @Size(max = 20, message = "Phone number must not exceed 20 characters.")
    private String phoneNumber;

    @NotBlank(message = "Hospital code is required.")
    @Size(max = 20, message = "Hospital code must not exceed 20 characters.")
    private String hospitalCode;

    @DecimalMin(value = "0.0", message = "Rating cannot be negative.")
    @DecimalMax(value = "5.0", message = "Rating cannot exceed 5.")
    private Double rating;
}