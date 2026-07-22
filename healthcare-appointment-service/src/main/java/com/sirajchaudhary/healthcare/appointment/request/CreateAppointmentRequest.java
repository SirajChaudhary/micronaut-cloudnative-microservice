package com.sirajchaudhary.healthcare.appointment.request;

import com.sirajchaudhary.healthcare.appointment.enums.AppointmentStatus;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class CreateAppointmentRequest {

    @NotBlank(message = "Hospital code is required.")
    private String hospitalCode;

    @NotBlank(message = "Doctor code is required.")
    private String doctorCode;

    @NotBlank(message = "Patient code is required.")
    private String patientCode;

    @NotNull(message = "Appointment date and time is required.")
    @Future(message = "Appointment date and time must be in the future.")
    private LocalDateTime appointmentDateTime;

    @NotNull(message = "Appointment status is required.")
    private AppointmentStatus status;

    @NotBlank(message = "Reason is required.")
    private String reason;

    private String diagnosis;

    private String prescription;

    private String notes;
}