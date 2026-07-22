package com.sirajchaudhary.healthcare.common.response;

import io.micronaut.serde.annotation.Serdeable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Serdeable
public class ApiResponse<T> {

    private boolean success;

    private String message;

    private T data;
}