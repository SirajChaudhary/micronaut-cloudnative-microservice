package com.sirajchaudhary.healthcare.hospital.controller;

import com.sirajchaudhary.healthcare.common.response.ApiResponse;
import com.sirajchaudhary.healthcare.common.response.PageResponse;
import com.sirajchaudhary.healthcare.hospital.request.CreateHospitalRequest;
import com.sirajchaudhary.healthcare.hospital.request.UpdateHospitalRequest;
import com.sirajchaudhary.healthcare.hospital.response.HospitalResponse;
import com.sirajchaudhary.healthcare.hospital.service.HospitalService;
import io.micronaut.data.model.Pageable;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import jakarta.validation.Valid;

/**
 * ============================================================================
 * Hospital REST APIs
 * ============================================================================
 *
 * This controller provides REST APIs to manage Hospital resources.
 *
 * Built using:
 * • Micronaut HTTP Controllers
 * • Micronaut Data JPA
 * • JPA/Hibernate
 * • Bean Validation
 * • Pageable for pagination
 *
 * Available APIs:
 * • POST   /hospitals              - Create a hospital
 * • GET    /hospitals/{hospitalCode} - Get hospital by code
 * • PUT    /hospitals/{hospitalCode} - Update hospital
 * • DELETE /hospitals/{hospitalCode} - Delete hospital
 * • GET    /hospitals              - List hospitals with pagination
 *
 * ============================================================================
 */
@Controller("/hospitals")
public class HospitalController {

    private final HospitalService hospitalService;

    public HospitalController(HospitalService hospitalService) {
        this.hospitalService = hospitalService;
    }

    @Post
    public HttpResponse<ApiResponse<HospitalResponse>> createHospital(
            @Body @Valid CreateHospitalRequest request) {

        HospitalResponse hospitalResponse = hospitalService.createHospital(request);

        ApiResponse<HospitalResponse> response = ApiResponse.<HospitalResponse>builder()
                .success(true)
                .message("Hospital created successfully.")
                .data(hospitalResponse)
                .build();

        return HttpResponse.created(response);
    }

    @Get("/{hospitalCode}")
    public HttpResponse<ApiResponse<HospitalResponse>> getHospitalByCode(
            @PathVariable String hospitalCode) {

        HospitalResponse hospitalResponse = hospitalService.getHospitalByCode(hospitalCode);

        ApiResponse<HospitalResponse> response = ApiResponse.<HospitalResponse>builder()
                .success(true)
                .message("Hospital retrieved successfully.")
                .data(hospitalResponse)
                .build();

        return HttpResponse.ok(response);
    }

    @Put("/{hospitalCode}")
    public HttpResponse<ApiResponse<HospitalResponse>> updateHospital(
            @PathVariable String hospitalCode,
            @Body @Valid UpdateHospitalRequest request) {

        HospitalResponse hospitalResponse =
                hospitalService.updateHospital(hospitalCode, request);

        ApiResponse<HospitalResponse> response = ApiResponse.<HospitalResponse>builder()
                .success(true)
                .message("Hospital updated successfully.")
                .data(hospitalResponse)
                .build();

        return HttpResponse.ok(response);
    }

    @Delete("/{hospitalCode}")
    public HttpResponse<ApiResponse<Void>> deleteHospital(
            @PathVariable String hospitalCode) {

        hospitalService.deleteHospital(hospitalCode);

        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .success(true)
                .message("Hospital deleted successfully.")
                .build();

        return HttpResponse.ok(response);
    }

    @Get
    public HttpResponse<ApiResponse<PageResponse<HospitalResponse>>> getAllHospitals(
            Pageable pageable) {

        PageResponse<HospitalResponse> hospitalPage =
                hospitalService.getAllHospitals(pageable);

        ApiResponse<PageResponse<HospitalResponse>> response =
                ApiResponse.<PageResponse<HospitalResponse>>builder()
                        .success(true)
                        .message("Hospitals retrieved successfully.")
                        .data(hospitalPage)
                        .build();

        return HttpResponse.ok(response);
    }
}