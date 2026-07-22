package com.sirajchaudhary.healthcare.common.response;

import io.micronaut.serde.annotation.Serdeable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Serdeable
public class ErrorResponse {

    private boolean success;
    private int status;
    private String message;
    private LocalDateTime timestamp;
}