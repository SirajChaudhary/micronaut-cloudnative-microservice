package com.sirajchaudhary.healthcare.common.exception;

import com.sirajchaudhary.healthcare.common.response.ErrorResponse;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import jakarta.inject.Singleton;

import java.time.LocalDateTime;

@Produces
@Singleton
public class GlobalExceptionHandler implements ExceptionHandler<RuntimeException, HttpResponse<ErrorResponse>> {

    @Override
    public HttpResponse<ErrorResponse> handle(HttpRequest request, RuntimeException exception) {

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        if (exception instanceof ResourceNotFoundException) {
            status = HttpStatus.NOT_FOUND;
        } else if (exception instanceof BadRequestException) {
            status = HttpStatus.BAD_REQUEST;
        }

        ErrorResponse response = ErrorResponse.builder()
                .success(false)
                .status(status.getCode())
                .message(exception.getMessage())
                .timestamp(LocalDateTime.now())
                .build();

        return HttpResponse.status(status).body(response);
    }
}