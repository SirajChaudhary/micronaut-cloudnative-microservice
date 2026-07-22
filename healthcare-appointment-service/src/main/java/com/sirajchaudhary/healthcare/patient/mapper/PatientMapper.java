package com.sirajchaudhary.healthcare.patient.mapper;

import com.sirajchaudhary.healthcare.hospital.entity.Hospital;
import com.sirajchaudhary.healthcare.patient.entity.Patient;
import com.sirajchaudhary.healthcare.patient.request.CreatePatientRequest;
import com.sirajchaudhary.healthcare.patient.request.UpdatePatientRequest;
import com.sirajchaudhary.healthcare.patient.response.PatientResponse;

import java.time.LocalDateTime;

public final class PatientMapper {

    private PatientMapper() {
    }

    public static Patient toEntity(CreatePatientRequest request,
                                   String patientCode,
                                   Hospital hospital) {

        LocalDateTime now = LocalDateTime.now();

        return Patient.builder()
                .patientCode(patientCode)
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .dateOfBirth(request.getDateOfBirth())
                .gender(request.getGender())
                .bloodGroup(request.getBloodGroup())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .addressLine1(request.getAddressLine1())
                .addressLine2(request.getAddressLine2())
                .city(request.getCity())
                .state(request.getState())
                .country(request.getCountry())
                .postalCode(request.getPostalCode())
                .hospital(hospital)
                .active(true)
                .createdAt(now)
                .updatedAt(now)
                .build();
    }

    public static void updateEntity(Patient patient,
                                    UpdatePatientRequest request,
                                    Hospital hospital) {

        patient.setFirstName(request.getFirstName());
        patient.setLastName(request.getLastName());
        patient.setDateOfBirth(request.getDateOfBirth());
        patient.setGender(request.getGender());
        patient.setBloodGroup(request.getBloodGroup());
        patient.setEmail(request.getEmail());
        patient.setPhoneNumber(request.getPhoneNumber());
        patient.setAddressLine1(request.getAddressLine1());
        patient.setAddressLine2(request.getAddressLine2());
        patient.setCity(request.getCity());
        patient.setState(request.getState());
        patient.setCountry(request.getCountry());
        patient.setPostalCode(request.getPostalCode());
        patient.setHospital(hospital);
        patient.setUpdatedAt(LocalDateTime.now());
    }

    public static PatientResponse toResponse(Patient patient) {

        return PatientResponse.builder()
                .id(patient.getId())
                .patientCode(patient.getPatientCode())
                .firstName(patient.getFirstName())
                .lastName(patient.getLastName())
                .dateOfBirth(patient.getDateOfBirth())
                .gender(patient.getGender())
                .bloodGroup(patient.getBloodGroup())
                .email(patient.getEmail())
                .phoneNumber(patient.getPhoneNumber())
                .addressLine1(patient.getAddressLine1())
                .addressLine2(patient.getAddressLine2())
                .city(patient.getCity())
                .state(patient.getState())
                .country(patient.getCountry())
                .postalCode(patient.getPostalCode())
                .hospitalCode(patient.getHospital().getHospitalCode())
                .hospitalName(patient.getHospital().getHospitalName())
                .active(patient.getActive())
                .createdAt(patient.getCreatedAt())
                .updatedAt(patient.getUpdatedAt())
                .build();
    }
}