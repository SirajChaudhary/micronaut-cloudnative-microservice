package com.sirajchaudhary.healthcare.hospital.mapper;

import com.sirajchaudhary.healthcare.hospital.entity.Hospital;
import com.sirajchaudhary.healthcare.hospital.request.CreateHospitalRequest;
import com.sirajchaudhary.healthcare.hospital.request.UpdateHospitalRequest;
import com.sirajchaudhary.healthcare.hospital.response.HospitalResponse;

import java.time.LocalDateTime;

public final class HospitalMapper {

    private HospitalMapper() {
    }

    public static Hospital toEntity(CreateHospitalRequest request, String hospitalCode) {

        LocalDateTime now = LocalDateTime.now();

        return Hospital.builder()
                .hospitalCode(hospitalCode)
                .hospitalName(request.getHospitalName())
                .registrationNumber(request.getRegistrationNumber())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .website(request.getWebsite())
                .addressLine1(request.getAddressLine1())
                .addressLine2(request.getAddressLine2())
                .city(request.getCity())
                .state(request.getState())
                .country(request.getCountry())
                .postalCode(request.getPostalCode())
                .active(true)
                .createdAt(now)
                .updatedAt(now)
                .build();
    }

    public static void updateEntity(Hospital hospital, UpdateHospitalRequest request) {

        hospital.setHospitalName(request.getHospitalName());
        hospital.setRegistrationNumber(request.getRegistrationNumber());
        hospital.setEmail(request.getEmail());
        hospital.setPhoneNumber(request.getPhoneNumber());
        hospital.setWebsite(request.getWebsite());
        hospital.setAddressLine1(request.getAddressLine1());
        hospital.setAddressLine2(request.getAddressLine2());
        hospital.setCity(request.getCity());
        hospital.setState(request.getState());
        hospital.setCountry(request.getCountry());
        hospital.setPostalCode(request.getPostalCode());
        hospital.setUpdatedAt(LocalDateTime.now());
    }

    public static HospitalResponse toResponse(Hospital hospital) {

        return HospitalResponse.builder()
                .id(hospital.getId())
                .hospitalCode(hospital.getHospitalCode())
                .hospitalName(hospital.getHospitalName())
                .registrationNumber(hospital.getRegistrationNumber())
                .email(hospital.getEmail())
                .phoneNumber(hospital.getPhoneNumber())
                .website(hospital.getWebsite())
                .addressLine1(hospital.getAddressLine1())
                .addressLine2(hospital.getAddressLine2())
                .city(hospital.getCity())
                .state(hospital.getState())
                .country(hospital.getCountry())
                .postalCode(hospital.getPostalCode())
                .active(hospital.getActive())
                .createdAt(hospital.getCreatedAt())
                .updatedAt(hospital.getUpdatedAt())
                .build();
    }
}