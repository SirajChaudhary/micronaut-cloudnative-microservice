package com.sirajchaudhary.healthcare.appointment.mapper;

import com.sirajchaudhary.healthcare.appointment.entity.Appointment;
import com.sirajchaudhary.healthcare.appointment.request.CreateAppointmentRequest;
import com.sirajchaudhary.healthcare.appointment.request.UpdateAppointmentRequest;
import com.sirajchaudhary.healthcare.appointment.response.AppointmentResponse;
import com.sirajchaudhary.healthcare.doctor.entity.Doctor;
import com.sirajchaudhary.healthcare.hospital.entity.Hospital;
import com.sirajchaudhary.healthcare.patient.entity.Patient;

public final class AppointmentMapper {

    private AppointmentMapper() {
    }

    public static Appointment toEntity(CreateAppointmentRequest request,
                                       String appointmentCode,
                                       Hospital hospital,
                                       Doctor doctor,
                                       Patient patient) {

        return Appointment.builder()
                .appointmentCode(appointmentCode)
                .hospital(hospital)
                .doctor(doctor)
                .patient(patient)
                .appointmentDateTime(request.getAppointmentDateTime())
                .status(request.getStatus())
                .reason(request.getReason())
                .diagnosis(request.getDiagnosis())
                .prescription(request.getPrescription())
                .notes(request.getNotes())
                .active(true)
                .build();
    }

    public static void updateEntity(Appointment appointment,
                                    UpdateAppointmentRequest request,
                                    Hospital hospital,
                                    Doctor doctor,
                                    Patient patient) {

        appointment.setHospital(hospital);
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        appointment.setAppointmentDateTime(request.getAppointmentDateTime());
        appointment.setStatus(request.getStatus());
        appointment.setReason(request.getReason());
        appointment.setDiagnosis(request.getDiagnosis());
        appointment.setPrescription(request.getPrescription());
        appointment.setNotes(request.getNotes());
    }

    public static AppointmentResponse toResponse(Appointment appointment) {

        return AppointmentResponse.builder()
                .id(appointment.getId())
                .appointmentCode(appointment.getAppointmentCode())

                .hospitalCode(appointment.getHospital().getHospitalCode())
                .hospitalName(appointment.getHospital().getHospitalName())

                .doctorCode(appointment.getDoctor().getDoctorCode())
                .doctorName(
                        appointment.getDoctor().getFirstName()
                                + " "
                                + appointment.getDoctor().getLastName())

                .patientCode(appointment.getPatient().getPatientCode())
                .patientName(
                        appointment.getPatient().getFirstName()
                                + " "
                                + appointment.getPatient().getLastName())

                .appointmentDateTime(appointment.getAppointmentDateTime())
                .status(appointment.getStatus())
                .reason(appointment.getReason())
                .diagnosis(appointment.getDiagnosis())
                .prescription(appointment.getPrescription())
                .notes(appointment.getNotes())
                .active(appointment.getActive())
                .createdAt(appointment.getCreatedAt())
                .updatedAt(appointment.getUpdatedAt())
                .build();
    }
}