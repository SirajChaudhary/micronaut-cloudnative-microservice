package com.sirajchaudhary.healthcare.appointment.response;

import com.sirajchaudhary.healthcare.appointment.enums.AppointmentStatus;
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
public class AppointmentResponse {

    private Long id;

    private String appointmentCode;

    private String hospitalCode;
    private String hospitalName;

    private String doctorCode;
    private String doctorName;

    private String patientCode;
    private String patientName;

    private LocalDateTime appointmentDateTime;

    private AppointmentStatus status;

    private String reason;

    private String diagnosis;

    private String prescription;

    private String notes;

    private Boolean active;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}