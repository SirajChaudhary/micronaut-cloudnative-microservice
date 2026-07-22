package com.sirajchaudhary.healthcare.hospital.service;

import com.sirajchaudhary.healthcare.common.exception.BadRequestException;
import com.sirajchaudhary.healthcare.common.exception.ResourceNotFoundException;
import com.sirajchaudhary.healthcare.common.response.PageResponse;
import com.sirajchaudhary.healthcare.hospital.entity.Hospital;
import com.sirajchaudhary.healthcare.hospital.mapper.HospitalMapper;
import com.sirajchaudhary.healthcare.hospital.repository.HospitalRepository;
import com.sirajchaudhary.healthcare.hospital.request.CreateHospitalRequest;
import com.sirajchaudhary.healthcare.hospital.request.UpdateHospitalRequest;
import com.sirajchaudhary.healthcare.hospital.response.HospitalResponse;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.micronaut.transaction.annotation.ReadOnly;
import io.micronaut.transaction.annotation.Transactional;
import jakarta.inject.Singleton;

import java.util.List;
import java.util.UUID;

@Singleton
public class HospitalService {

    private final HospitalRepository hospitalRepository;

    public HospitalService(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }

    @Transactional
    public HospitalResponse createHospital(CreateHospitalRequest request) {

        if (hospitalRepository.existsByRegistrationNumber(request.getRegistrationNumber())) {
            throw new BadRequestException(
                    "Hospital already exists with registration number: "
                            + request.getRegistrationNumber());
        }

        if (hospitalRepository.existsByEmail(request.getEmail())) {
            throw new BadRequestException(
                    "Hospital already exists with email: " + request.getEmail());
        }

        String hospitalCode = generateHospitalCode();

        Hospital hospital = HospitalMapper.toEntity(request, hospitalCode);

        Hospital savedHospital = hospitalRepository.save(hospital);

        return HospitalMapper.toResponse(savedHospital);
    }

    @ReadOnly
    public HospitalResponse getHospitalByCode(String hospitalCode) {

        Hospital hospital = hospitalRepository.findByHospitalCode(hospitalCode)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Hospital not found with code: " + hospitalCode));

        return HospitalMapper.toResponse(hospital);
    }

    @Transactional
    public HospitalResponse updateHospital(String hospitalCode,
                                           UpdateHospitalRequest request) {

        Hospital hospital = hospitalRepository.findByHospitalCode(hospitalCode)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Hospital not found with code: " + hospitalCode));

        if (!hospital.getEmail().equalsIgnoreCase(request.getEmail())
                && hospitalRepository.existsByEmail(request.getEmail())) {

            throw new BadRequestException(
                    "Hospital already exists with email: " + request.getEmail());
        }

        if (!hospital.getRegistrationNumber().equalsIgnoreCase(request.getRegistrationNumber())
                && hospitalRepository.existsByRegistrationNumber(request.getRegistrationNumber())) {

            throw new BadRequestException(
                    "Hospital already exists with registration number: "
                            + request.getRegistrationNumber());
        }

        HospitalMapper.updateEntity(hospital, request);

        Hospital updatedHospital = hospitalRepository.update(hospital);

        return HospitalMapper.toResponse(updatedHospital);
    }

    @Transactional
    public void deleteHospital(String hospitalCode) {

        Hospital hospital = hospitalRepository.findByHospitalCode(hospitalCode)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Hospital not found with code: " + hospitalCode));

        hospitalRepository.delete(hospital);
    }

    @ReadOnly
    public PageResponse<HospitalResponse> getAllHospitals(Pageable pageable) {

        Page<Hospital> page = hospitalRepository.findAll(pageable);

        List<HospitalResponse> hospitals = page.getContent()
                .stream()
                .map(HospitalMapper::toResponse)
                .toList();

        return PageResponse.<HospitalResponse>builder()
                .content(hospitals)
                .pageNumber(page.getPageNumber())
                .pageSize(page.getSize())
                .totalElements(page.getTotalSize())
                .totalPages(page.getTotalPages())
                .first(page.getPageNumber() == 0)
                .last(page.getPageNumber() >= page.getTotalPages() - 1)
                .empty(hospitals.isEmpty())
                .build();
    }

    private String generateHospitalCode() {
        return "HOS-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}