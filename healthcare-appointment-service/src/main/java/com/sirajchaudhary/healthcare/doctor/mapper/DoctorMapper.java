package com.sirajchaudhary.healthcare.doctor.mapper;

import com.sirajchaudhary.healthcare.doctor.entity.Doctor;
import com.sirajchaudhary.healthcare.doctor.request.CreateDoctorRequest;
import com.sirajchaudhary.healthcare.doctor.request.UpdateDoctorRequest;
import com.sirajchaudhary.healthcare.doctor.response.DoctorResponse;
import com.sirajchaudhary.healthcare.hospital.entity.Hospital;

import java.time.LocalDateTime;

public final class DoctorMapper {

    private DoctorMapper() {
    }

    public static Doctor toEntity(CreateDoctorRequest request,
                                  String doctorCode,
                                  Hospital hospital) {

        LocalDateTime now = LocalDateTime.now();

        return Doctor.builder()
                .doctorCode(doctorCode)
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .specialization(request.getSpecialization())
                .qualification(request.getQualification())
                .experience(request.getExperience())
                .consultationFee(request.getConsultationFee())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .hospital(hospital)
                .rating(request.getRating())
                .active(true)
                .createdAt(now)
                .updatedAt(now)
                .build();
    }

    public static void updateEntity(Doctor doctor,
                                    UpdateDoctorRequest request,
                                    Hospital hospital) {

        doctor.setFirstName(request.getFirstName());
        doctor.setLastName(request.getLastName());
        doctor.setSpecialization(request.getSpecialization());
        doctor.setQualification(request.getQualification());
        doctor.setExperience(request.getExperience());
        doctor.setConsultationFee(request.getConsultationFee());
        doctor.setEmail(request.getEmail());
        doctor.setPhoneNumber(request.getPhoneNumber());
        doctor.setHospital(hospital);
        doctor.setRating(request.getRating());
        doctor.setUpdatedAt(LocalDateTime.now());
    }

    public static DoctorResponse toResponse(Doctor doctor) {

        return DoctorResponse.builder()
                .id(doctor.getId())
                .doctorCode(doctor.getDoctorCode())
                .firstName(doctor.getFirstName())
                .lastName(doctor.getLastName())
                .specialization(doctor.getSpecialization())
                .qualification(doctor.getQualification())
                .experience(doctor.getExperience())
                .consultationFee(doctor.getConsultationFee())
                .email(doctor.getEmail())
                .phoneNumber(doctor.getPhoneNumber())
                .hospitalCode(doctor.getHospital().getHospitalCode())
                .hospitalName(doctor.getHospital().getHospitalName())
                .rating(doctor.getRating())
                .active(doctor.getActive())
                .createdAt(doctor.getCreatedAt())
                .updatedAt(doctor.getUpdatedAt())
                .build();
    }
}