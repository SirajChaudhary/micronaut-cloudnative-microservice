package com.sirajchaudhary.healthcare.doctor.service;

import com.sirajchaudhary.healthcare.common.exception.BadRequestException;
import com.sirajchaudhary.healthcare.common.exception.ResourceNotFoundException;
import com.sirajchaudhary.healthcare.common.response.PageResponse;
import com.sirajchaudhary.healthcare.doctor.entity.Doctor;
import com.sirajchaudhary.healthcare.doctor.mapper.DoctorMapper;
import com.sirajchaudhary.healthcare.doctor.repository.DoctorRepository;
import com.sirajchaudhary.healthcare.doctor.request.CreateDoctorRequest;
import com.sirajchaudhary.healthcare.doctor.request.UpdateDoctorRequest;
import com.sirajchaudhary.healthcare.doctor.response.DoctorResponse;
import com.sirajchaudhary.healthcare.hospital.entity.Hospital;
import com.sirajchaudhary.healthcare.hospital.repository.HospitalRepository;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.micronaut.transaction.annotation.ReadOnly;
import io.micronaut.transaction.annotation.Transactional;
import jakarta.inject.Singleton;

import java.util.List;
import java.util.UUID;

@Singleton
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final HospitalRepository hospitalRepository;

    public DoctorService(DoctorRepository doctorRepository,
                         HospitalRepository hospitalRepository) {
        this.doctorRepository = doctorRepository;
        this.hospitalRepository = hospitalRepository;
    }

    @Transactional
    public DoctorResponse createDoctor(CreateDoctorRequest request) {

        if (doctorRepository.existsByEmail(request.getEmail())) {
            throw new BadRequestException(
                    "Doctor already exists with email: " + request.getEmail());
        }

        Hospital hospital = hospitalRepository.findByHospitalCode(request.getHospitalCode())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Hospital not found with code: " + request.getHospitalCode()));

        String doctorCode = generateDoctorCode();

        Doctor doctor = DoctorMapper.toEntity(request, doctorCode, hospital);

        Doctor savedDoctor = doctorRepository.save(doctor);

        return DoctorMapper.toResponse(savedDoctor);
    }

    @ReadOnly
    public DoctorResponse getDoctorByCode(String doctorCode) {

        Doctor doctor = doctorRepository.findByDoctorCode(doctorCode)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Doctor not found with code: " + doctorCode));

        return DoctorMapper.toResponse(doctor);
    }

    @Transactional
    public DoctorResponse updateDoctor(String doctorCode,
                                       UpdateDoctorRequest request) {

        Doctor doctor = doctorRepository.findByDoctorCode(doctorCode)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Doctor not found with code: " + doctorCode));

        if (!doctor.getEmail().equalsIgnoreCase(request.getEmail())
                && doctorRepository.existsByEmail(request.getEmail())) {

            throw new BadRequestException(
                    "Doctor already exists with email: " + request.getEmail());
        }

        Hospital hospital = hospitalRepository.findByHospitalCode(request.getHospitalCode())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Hospital not found with code: " + request.getHospitalCode()));

        DoctorMapper.updateEntity(doctor, request, hospital);

        Doctor updatedDoctor = doctorRepository.update(doctor);

        return DoctorMapper.toResponse(updatedDoctor);
    }

    @Transactional
    public void deleteDoctor(String doctorCode) {

        Doctor doctor = doctorRepository.findByDoctorCode(doctorCode)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Doctor not found with code: " + doctorCode));

        doctorRepository.delete(doctor);
    }

    @ReadOnly
    public PageResponse<DoctorResponse> getAllDoctors(Pageable pageable) {

        Page<Doctor> page = doctorRepository.findAll(pageable);

        List<DoctorResponse> doctors = page.getContent()
                .stream()
                .map(DoctorMapper::toResponse)
                .toList();

        return PageResponse.<DoctorResponse>builder()
                .content(doctors)
                .pageNumber(page.getPageNumber())
                .pageSize(page.getSize())
                .totalElements(page.getTotalSize())
                .totalPages(page.getTotalPages())
                .first(page.getPageNumber() == 0)
                .last(page.getPageNumber() >= page.getTotalPages() - 1)
                .empty(doctors.isEmpty())
                .build();
    }

    @ReadOnly
    public PageResponse<DoctorResponse> searchDoctors(
            String q,
            String hospitalCode,
            String specialization,
            Integer minExperience,
            Double minRating,
            Boolean active,
            Pageable pageable) {

        String qLike = (q == null || q.isBlank())
                ? null
                : "%" + q.trim().toLowerCase() + "%";

        String specLower = (specialization == null || specialization.isBlank())
                ? null
                : specialization.trim().toLowerCase();

        Page<Doctor> page = doctorRepository.searchDoctors(
                qLike,
                emptyToNull(hospitalCode),
                specLower,
                minExperience,
                minRating,
                active,
                pageable
        );

        List<DoctorResponse> content = page.getContent()
                .stream()
                .map(DoctorMapper::toResponse)
                .toList();

        return PageResponse.<DoctorResponse>builder()
                .content(content)
                .pageNumber(page.getPageNumber())
                .pageSize(page.getSize())
                .totalElements(page.getTotalSize())
                .totalPages(page.getTotalPages())
                .first(page.getPageNumber() == 0)
                .last(page.getPageNumber() >= page.getTotalPages() - 1)
                .empty(content.isEmpty())
                .build();
    }

    private String emptyToNull(String value) {
        return (value == null || value.isBlank()) ? null : value.trim();
    }

    private String generateDoctorCode() {
        return "DOC-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}