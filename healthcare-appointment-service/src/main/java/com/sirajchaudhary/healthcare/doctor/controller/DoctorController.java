package com.sirajchaudhary.healthcare.doctor.controller;

import com.sirajchaudhary.healthcare.common.response.ApiResponse;
import com.sirajchaudhary.healthcare.common.response.PageResponse;
import com.sirajchaudhary.healthcare.doctor.request.CreateDoctorRequest;
import com.sirajchaudhary.healthcare.doctor.request.UpdateDoctorRequest;
import com.sirajchaudhary.healthcare.doctor.response.DoctorResponse;
import com.sirajchaudhary.healthcare.doctor.service.DoctorService;
import io.micronaut.data.model.Pageable;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;

/*
 * ============================================================================
 * Doctor REST APIs
 * ============================================================================
 *
 * This controller exposes REST endpoints to manage doctors.
 *
 * APIs:
 * • Create a doctor
 * • Get doctor details by doctor code
 * • Update doctor information
 * • Delete a doctor
 * • List doctors with pagination
 * • Search doctors (recommended API)
 *
 * The Search API demonstrates real-world search capabilities including:
 * • Keyword search
 * • Multiple filters
 * • Pagination
 * • Sorting
 * • Dynamic query construction
 * • Optional query parameters
 *
 * ============================================================================
 */
@Controller("/doctors")
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @Post
    public HttpResponse<ApiResponse<DoctorResponse>> createDoctor(
            @Body @Valid CreateDoctorRequest request) {

        DoctorResponse doctorResponse = doctorService.createDoctor(request);

        ApiResponse<DoctorResponse> response = ApiResponse.<DoctorResponse>builder()
                .success(true)
                .message("Doctor created successfully.")
                .data(doctorResponse)
                .build();

        return HttpResponse.created(response);
    }

    @Get("/{doctorCode}")
    public HttpResponse<ApiResponse<DoctorResponse>> getDoctorByCode(
            @PathVariable String doctorCode) {

        DoctorResponse doctorResponse = doctorService.getDoctorByCode(doctorCode);

        ApiResponse<DoctorResponse> response = ApiResponse.<DoctorResponse>builder()
                .success(true)
                .message("Doctor retrieved successfully.")
                .data(doctorResponse)
                .build();

        return HttpResponse.ok(response);
    }

    @Put("/{doctorCode}")
    public HttpResponse<ApiResponse<DoctorResponse>> updateDoctor(
            @PathVariable String doctorCode,
            @Body @Valid UpdateDoctorRequest request) {

        DoctorResponse doctorResponse = doctorService.updateDoctor(doctorCode, request);

        ApiResponse<DoctorResponse> response = ApiResponse.<DoctorResponse>builder()
                .success(true)
                .message("Doctor updated successfully.")
                .data(doctorResponse)
                .build();

        return HttpResponse.ok(response);
    }

    @Delete("/{doctorCode}")
    public HttpResponse<ApiResponse<Void>> deleteDoctor(
            @PathVariable String doctorCode) {

        doctorService.deleteDoctor(doctorCode);

        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .success(true)
                .message("Doctor deleted successfully.")
                .build();

        return HttpResponse.ok(response);
    }

    @Get
    public HttpResponse<ApiResponse<PageResponse<DoctorResponse>>> getAllDoctors(
            Pageable pageable) {

        PageResponse<DoctorResponse> doctorPage = doctorService.getAllDoctors(pageable);

        ApiResponse<PageResponse<DoctorResponse>> response =
                ApiResponse.<PageResponse<DoctorResponse>>builder()
                        .success(true)
                        .message("Doctors retrieved successfully.")
                        .data(doctorPage)
                        .build();

        return HttpResponse.ok(response);
    }

    /*
     * =========================================================================
     * Advanced Doctor Search
     * =========================================================================
     *
     * Demonstrates a real-world enterprise search API built using
     * Micronaut Data JPA Specifications.
     *
     * Features:
     * • Partial text search (case-insensitive)
     * • Dynamic filtering
     * • Pagination
     * • Single and multi-column sorting
     * • Optional query parameters
     * • Dynamic query generation (only supplied filters are applied)
     *
     * Partial Search (q)
     * ------------------
     * Searches using a case-insensitive substring match across:
     * • firstName
     * • lastName
     * • specialization
     * • email
     *
     * Examples
     * • GET /doctors/search?q=amit
     * • GET /doctors/search?q=cardio
     * • GET /doctors/search?q=hospital.com
     *
     * Exact Filters
     * -------------
     * Hospital
     * • GET /doctors/search?hospitalCode=HOS-000001
     *
     * Specialization
     * • GET /doctors/search?specialization=Cardiology
     * • GET /doctors/search?specialization=Neurology
     *
     * Experience
     * • GET /doctors/search?minExperience=15
     *
     * Rating
     * • GET /doctors/search?minRating=4.8
     *
     * Active Status
     * • GET /doctors/search?active=true
     *
     * Combined Search
     * ---------------
     * • GET /doctors/search?q=cardio&hospitalCode=HOS-000001
     * • GET /doctors/search?q=amit&minRating=4.8
     * • GET /doctors/search?specialization=General%20Medicine&active=true
     * • GET /doctors/search?hospitalCode=HOS-000005&minExperience=20&minRating=4.9
     *
     * Pagination
     * ----------
     * • GET /doctors/search?page=0&size=10
     * • GET /doctors/search?page=1&size=5
     *
     * Sorting
     * -------
     * Supported properties:
     * doctorCode, firstName, lastName, specialization, experience,
     * consultationFee, email, phoneNumber, rating, active,
     * createdAt, updatedAt
     *
     * Examples
     * • GET /doctors/search?sort=rating,desc
     * • GET /doctors/search?sort=experience,desc
     * • GET /doctors/search?sort=lastName,asc&sort=firstName,asc
     * • GET /doctors/search?hospitalCode=HOS-000001&sort=rating,desc
     *
     * =========================================================================
     */
    @Get("/search") public HttpResponse<ApiResponse<PageResponse<DoctorResponse>>> searchDoctors( @Nullable @QueryValue String q, @Nullable @QueryValue String hospitalCode, @Nullable @QueryValue String specialization, @Nullable @QueryValue Integer minExperience, @Nullable @QueryValue Double minRating, @Nullable @QueryValue Boolean active, Pageable pageable) {
        PageResponse<DoctorResponse> pageResponse = doctorService.searchDoctors(
                q, hospitalCode, specialization, minExperience, minRating, active, pageable);

        ApiResponse<PageResponse<DoctorResponse>> response =
                ApiResponse.<PageResponse<DoctorResponse>>builder()
                        .success(true)
                        .message("Doctors search results.")
                        .data(pageResponse)
                        .build();

        return HttpResponse.ok(response);
    }
}