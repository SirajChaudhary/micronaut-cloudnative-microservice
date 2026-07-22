package com.sirajchaudhary.healthcare.appointment.controller;

import com.sirajchaudhary.healthcare.appointment.request.CreateAppointmentRequest;
import com.sirajchaudhary.healthcare.appointment.request.UpdateAppointmentRequest;
import com.sirajchaudhary.healthcare.appointment.response.AppointmentResponse;
import com.sirajchaudhary.healthcare.appointment.service.AppointmentService;
import com.sirajchaudhary.healthcare.common.response.ApiResponse;
import com.sirajchaudhary.healthcare.common.response.PageResponse;
import io.micronaut.data.model.Pageable;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import jakarta.validation.Valid;

/**
 * ============================================================================
 * Appointment REST APIs
 * ============================================================================
 *
 * This controller provides REST APIs to manage Appointment resources.
 *
 * Built using:
 * • Micronaut HTTP Controllers
 * • Micronaut Data JPA
 * • JPA/Hibernate
 * • Bean Validation
 * • Pageable for pagination
 *
 * Available APIs:
 * • POST   /appointments                   - Create an appointment
 * • GET    /appointments/{appointmentCode} - Get appointment by code
 * • PUT    /appointments/{appointmentCode} - Update appointment
 * • DELETE /appointments/{appointmentCode} - Delete appointment
 * • GET    /appointments                   - List appointments with pagination
 *
 * ============================================================================
 */
@Controller("/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @Post
    public HttpResponse<ApiResponse<AppointmentResponse>> createAppointment(
            @Body @Valid CreateAppointmentRequest request) {

        AppointmentResponse appointmentResponse =
                appointmentService.createAppointment(request);

        ApiResponse<AppointmentResponse> response =
                ApiResponse.<AppointmentResponse>builder()
                        .success(true)
                        .message("Appointment created successfully.")
                        .data(appointmentResponse)
                        .build();

        return HttpResponse.created(response);
    }

    @Get("/{appointmentCode}")
    public HttpResponse<ApiResponse<AppointmentResponse>> getAppointmentByCode(
            @PathVariable String appointmentCode) {

        AppointmentResponse appointmentResponse =
                appointmentService.getAppointmentByCode(appointmentCode);

        ApiResponse<AppointmentResponse> response =
                ApiResponse.<AppointmentResponse>builder()
                        .success(true)
                        .message("Appointment retrieved successfully.")
                        .data(appointmentResponse)
                        .build();

        return HttpResponse.ok(response);
    }

    @Put("/{appointmentCode}")
    public HttpResponse<ApiResponse<AppointmentResponse>> updateAppointment(
            @PathVariable String appointmentCode,
            @Body @Valid UpdateAppointmentRequest request) {

        AppointmentResponse appointmentResponse =
                appointmentService.updateAppointment(appointmentCode, request);

        ApiResponse<AppointmentResponse> response =
                ApiResponse.<AppointmentResponse>builder()
                        .success(true)
                        .message("Appointment updated successfully.")
                        .data(appointmentResponse)
                        .build();

        return HttpResponse.ok(response);
    }

    @Delete("/{appointmentCode}")
    public HttpResponse<ApiResponse<Void>> deleteAppointment(
            @PathVariable String appointmentCode) {

        appointmentService.deleteAppointment(appointmentCode);

        ApiResponse<Void> response =
                ApiResponse.<Void>builder()
                        .success(true)
                        .message("Appointment deleted successfully.")
                        .build();

        return HttpResponse.ok(response);
    }

    @Get
    public HttpResponse<ApiResponse<PageResponse<AppointmentResponse>>> getAllAppointments(
            Pageable pageable) {

        PageResponse<AppointmentResponse> pageResponse =
                appointmentService.getAllAppointments(pageable);

        ApiResponse<PageResponse<AppointmentResponse>> response =
                ApiResponse.<PageResponse<AppointmentResponse>>builder()
                        .success(true)
                        .message("Appointments retrieved successfully.")
                        .data(pageResponse)
                        .build();

        return HttpResponse.ok(response);
    }
}