package com.sirajchaudhary.healthcare.patient.service;

import com.sirajchaudhary.healthcare.common.exception.BadRequestException;
import com.sirajchaudhary.healthcare.common.exception.ResourceNotFoundException;
import com.sirajchaudhary.healthcare.common.response.PageResponse;
import com.sirajchaudhary.healthcare.hospital.entity.Hospital;
import com.sirajchaudhary.healthcare.hospital.repository.HospitalRepository;
import com.sirajchaudhary.healthcare.patient.entity.Patient;
import com.sirajchaudhary.healthcare.patient.mapper.PatientMapper;
import com.sirajchaudhary.healthcare.patient.repository.PatientRepository;
import com.sirajchaudhary.healthcare.patient.request.CreatePatientRequest;
import com.sirajchaudhary.healthcare.patient.request.UpdatePatientRequest;
import com.sirajchaudhary.healthcare.patient.response.PatientResponse;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.micronaut.transaction.annotation.ReadOnly;
import io.micronaut.transaction.annotation.Transactional;
import jakarta.inject.Singleton;

import java.util.List;
import java.util.UUID;

@Singleton
public class PatientService {

    private final PatientRepository patientRepository;
    private final HospitalRepository hospitalRepository;

    public PatientService(PatientRepository patientRepository,
                          HospitalRepository hospitalRepository) {
        this.patientRepository = patientRepository;
        this.hospitalRepository = hospitalRepository;
    }

    @Transactional
    public PatientResponse createPatient(CreatePatientRequest request) {

        if (patientRepository.existsByEmail(request.getEmail())) {
            throw new BadRequestException(
                    "Patient already exists with email: " + request.getEmail());
        }

        Hospital hospital = hospitalRepository.findByHospitalCode(request.getHospitalCode())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Hospital not found with code: " + request.getHospitalCode()));

        String patientCode = generatePatientCode();

        Patient patient = PatientMapper.toEntity(request, patientCode, hospital);

        Patient savedPatient = patientRepository.save(patient);

        return PatientMapper.toResponse(savedPatient);
    }

    @ReadOnly
    public PatientResponse getPatientByCode(String patientCode) {

        Patient patient = patientRepository.findByPatientCode(patientCode)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Patient not found with code: " + patientCode));

        return PatientMapper.toResponse(patient);
    }

    @Transactional
    public PatientResponse updatePatient(String patientCode,
                                         UpdatePatientRequest request) {

        Patient patient = patientRepository.findByPatientCode(patientCode)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Patient not found with code: " + patientCode));

        if (!patient.getEmail().equalsIgnoreCase(request.getEmail())
                && patientRepository.existsByEmail(request.getEmail())) {

            throw new BadRequestException(
                    "Patient already exists with email: " + request.getEmail());
        }

        Hospital hospital = hospitalRepository.findByHospitalCode(request.getHospitalCode())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Hospital not found with code: " + request.getHospitalCode()));

        PatientMapper.updateEntity(patient, request, hospital);

        Patient updatedPatient = patientRepository.update(patient);

        return PatientMapper.toResponse(updatedPatient);
    }

    @Transactional
    public void deletePatient(String patientCode) {

        Patient patient = patientRepository.findByPatientCode(patientCode)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Patient not found with code: " + patientCode));

        patientRepository.delete(patient);
    }

    @ReadOnly
    public PageResponse<PatientResponse> getAllPatients(Pageable pageable) {

        Page<Patient> page = patientRepository.findAll(pageable);

        List<PatientResponse> patients = page.getContent()
                .stream()
                .map(PatientMapper::toResponse)
                .toList();

        return PageResponse.<PatientResponse>builder()
                .content(patients)
                .pageNumber(page.getPageNumber())
                .pageSize(page.getSize())
                .totalElements(page.getTotalSize())
                .totalPages(page.getTotalPages())
                .first(page.getPageNumber() == 0)
                .last(page.getPageNumber() >= page.getTotalPages() - 1)
                .empty(patients.isEmpty())
                .build();
    }

    private String generatePatientCode() {
        return "PAT-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}