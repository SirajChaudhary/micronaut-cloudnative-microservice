package com.sirajchaudhary.healthcare.doctor.response;

import io.micronaut.serde.annotation.Serdeable;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Serdeable
public class DoctorResponse {

    private Long id;

    private String doctorCode;

    private String firstName;

    private String lastName;

    private String specialization;

    private String qualification;

    private Integer experience;

    private BigDecimal consultationFee;

    private String email;

    private String phoneNumber;

    private String hospitalCode;

    private String hospitalName;

    private Double rating;

    private Boolean active;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}