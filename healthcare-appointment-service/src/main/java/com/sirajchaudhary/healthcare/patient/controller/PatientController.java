package com.sirajchaudhary.healthcare.patient.controller;

import com.sirajchaudhary.healthcare.common.response.ApiResponse;
import com.sirajchaudhary.healthcare.common.response.PageResponse;
import com.sirajchaudhary.healthcare.patient.request.CreatePatientRequest;
import com.sirajchaudhary.healthcare.patient.request.UpdatePatientRequest;
import com.sirajchaudhary.healthcare.patient.response.PatientResponse;
import com.sirajchaudhary.healthcare.patient.service.PatientService;
import io.micronaut.data.model.Pageable;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import jakarta.validation.Valid;

/**
 * ============================================================================
 * Patient REST APIs
 * ============================================================================
 *
 * This controller provides REST APIs to manage Patient resources.
 *
 * Built using:
 * • Micronaut HTTP Controllers
 * • Micronaut Data JPA
 * • JPA/Hibernate
 * • Bean Validation
 * • Pageable for pagination
 *
 * Available APIs:
 * • POST   /patients                - Create a patient
 * • GET    /patients/{patientCode}  - Get patient by code
 * • PUT    /patients/{patientCode}  - Update patient
 * • DELETE /patients/{patientCode}  - Delete patient
 * • GET    /patients                - List patients with pagination
 *
 * ============================================================================
 */
@Controller("/patients")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @Post
    public HttpResponse<ApiResponse<PatientResponse>> createPatient(
            @Body @Valid CreatePatientRequest request) {

        PatientResponse patientResponse = patientService.createPatient(request);

        ApiResponse<PatientResponse> response = ApiResponse.<PatientResponse>builder()
                .success(true)
                .message("Patient created successfully.")
                .data(patientResponse)
                .build();

        return HttpResponse.created(response);
    }

    @Get("/{patientCode}")
    public HttpResponse<ApiResponse<PatientResponse>> getPatientByCode(
            @PathVariable String patientCode) {

        PatientResponse patientResponse = patientService.getPatientByCode(patientCode);

        ApiResponse<PatientResponse> response = ApiResponse.<PatientResponse>builder()
                .success(true)
                .message("Patient retrieved successfully.")
                .data(patientResponse)
                .build();

        return HttpResponse.ok(response);
    }

    @Put("/{patientCode}")
    public HttpResponse<ApiResponse<PatientResponse>> updatePatient(
            @PathVariable String patientCode,
            @Body @Valid UpdatePatientRequest request) {

        PatientResponse patientResponse =
                patientService.updatePatient(patientCode, request);

        ApiResponse<PatientResponse> response = ApiResponse.<PatientResponse>builder()
                .success(true)
                .message("Patient updated successfully.")
                .data(patientResponse)
                .build();

        return HttpResponse.ok(response);
    }

    @Delete("/{patientCode}")
    public HttpResponse<ApiResponse<Void>> deletePatient(
            @PathVariable String patientCode) {

        patientService.deletePatient(patientCode);

        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .success(true)
                .message("Patient deleted successfully.")
                .build();

        return HttpResponse.ok(response);
    }

    @Get
    public HttpResponse<ApiResponse<PageResponse<PatientResponse>>> getAllPatients(
            Pageable pageable) {

        PageResponse<PatientResponse> patientPage =
                patientService.getAllPatients(pageable);

        ApiResponse<PageResponse<PatientResponse>> response =
                ApiResponse.<PageResponse<PatientResponse>>builder()
                        .success(true)
                        .message("Patients retrieved successfully.")
                        .data(patientPage)
                        .build();

        return HttpResponse.ok(response);
    }
}