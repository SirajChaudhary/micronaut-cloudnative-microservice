package com.sirajchaudhary.healthcare.appointment.service;

import com.sirajchaudhary.healthcare.appointment.entity.Appointment;
import com.sirajchaudhary.healthcare.appointment.mapper.AppointmentMapper;
import com.sirajchaudhary.healthcare.appointment.repository.AppointmentRepository;
import com.sirajchaudhary.healthcare.appointment.request.CreateAppointmentRequest;
import com.sirajchaudhary.healthcare.appointment.request.UpdateAppointmentRequest;
import com.sirajchaudhary.healthcare.appointment.response.AppointmentResponse;
import com.sirajchaudhary.healthcare.common.exception.BadRequestException;
import com.sirajchaudhary.healthcare.common.exception.ResourceNotFoundException;
import com.sirajchaudhary.healthcare.common.response.PageResponse;
import com.sirajchaudhary.healthcare.doctor.entity.Doctor;
import com.sirajchaudhary.healthcare.doctor.repository.DoctorRepository;
import com.sirajchaudhary.healthcare.hospital.entity.Hospital;
import com.sirajchaudhary.healthcare.hospital.repository.HospitalRepository;
import com.sirajchaudhary.healthcare.patient.entity.Patient;
import com.sirajchaudhary.healthcare.patient.repository.PatientRepository;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.micronaut.transaction.annotation.ReadOnly;
import io.micronaut.transaction.annotation.Transactional;
import jakarta.inject.Singleton;

import java.util.List;
import java.util.UUID;

@Singleton
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final HospitalRepository hospitalRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    public AppointmentService(AppointmentRepository appointmentRepository,
                              HospitalRepository hospitalRepository,
                              DoctorRepository doctorRepository,
                              PatientRepository patientRepository) {
        this.appointmentRepository = appointmentRepository;
        this.hospitalRepository = hospitalRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
    }

    @Transactional
    public AppointmentResponse createAppointment(CreateAppointmentRequest request) {

        Hospital hospital = hospitalRepository.findByHospitalCode(request.getHospitalCode())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Hospital not found with code: " + request.getHospitalCode()));

        Doctor doctor = doctorRepository.findByDoctorCode(request.getDoctorCode())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Doctor not found with code: " + request.getDoctorCode()));

        Patient patient = patientRepository.findByPatientCode(request.getPatientCode())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Patient not found with code: " + request.getPatientCode()));

        validateHospitalRelations(hospital, doctor, patient);

        if (appointmentRepository.existsByDoctorAndAppointmentDateTime(
                doctor,
                request.getAppointmentDateTime())) {

            throw new BadRequestException(
                    "Doctor already has an appointment at the selected date and time.");
        }

        Appointment appointment = AppointmentMapper.toEntity(
                request,
                generateAppointmentCode(),
                hospital,
                doctor,
                patient);

        Appointment savedAppointment = appointmentRepository.save(appointment);

        return AppointmentMapper.toResponse(savedAppointment);
    }

    @ReadOnly
    public AppointmentResponse getAppointmentByCode(String appointmentCode) {

        Appointment appointment = appointmentRepository.findByAppointmentCode(appointmentCode)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Appointment not found with code: " + appointmentCode));

        return AppointmentMapper.toResponse(appointment);
    }

    @Transactional
    public AppointmentResponse updateAppointment(String appointmentCode,
                                                 UpdateAppointmentRequest request) {

        Appointment appointment = appointmentRepository.findByAppointmentCode(appointmentCode)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Appointment not found with code: " + appointmentCode));

        Hospital hospital = hospitalRepository.findByHospitalCode(request.getHospitalCode())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Hospital not found with code: " + request.getHospitalCode()));

        Doctor doctor = doctorRepository.findByDoctorCode(request.getDoctorCode())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Doctor not found with code: " + request.getDoctorCode()));

        Patient patient = patientRepository.findByPatientCode(request.getPatientCode())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Patient not found with code: " + request.getPatientCode()));

        validateHospitalRelations(hospital, doctor, patient);

        boolean appointmentChanged =
                !appointment.getDoctor().getId().equals(doctor.getId())
                        || !appointment.getAppointmentDateTime()
                        .equals(request.getAppointmentDateTime());

        if (appointmentChanged &&
                appointmentRepository.existsByDoctorAndAppointmentDateTime(
                        doctor,
                        request.getAppointmentDateTime())) {

            throw new BadRequestException(
                    "Doctor already has an appointment at the selected date and time.");
        }

        AppointmentMapper.updateEntity(
                appointment,
                request,
                hospital,
                doctor,
                patient);

        Appointment updatedAppointment = appointmentRepository.update(appointment);

        return AppointmentMapper.toResponse(updatedAppointment);
    }

    @Transactional
    public void deleteAppointment(String appointmentCode) {

        Appointment appointment = appointmentRepository.findByAppointmentCode(appointmentCode)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Appointment not found with code: " + appointmentCode));

        appointmentRepository.delete(appointment);
    }

    @ReadOnly
    public PageResponse<AppointmentResponse> getAllAppointments(Pageable pageable) {

        Page<Appointment> page = appointmentRepository.findAll(pageable);

        List<AppointmentResponse> appointments = page.getContent()
                .stream()
                .map(AppointmentMapper::toResponse)
                .toList();

        return PageResponse.<AppointmentResponse>builder()
                .content(appointments)
                .pageNumber(page.getPageNumber())
                .pageSize(page.getSize())
                .totalElements(page.getTotalSize())
                .totalPages(page.getTotalPages())
                .first(page.getPageNumber() == 0)
                .last(page.getPageNumber() >= page.getTotalPages() - 1)
                .empty(appointments.isEmpty())
                .build();
    }

    private void validateHospitalRelations(Hospital hospital,
                                           Doctor doctor,
                                           Patient patient) {

        if (!doctor.getHospital().getId().equals(hospital.getId())) {
            throw new BadRequestException(
                    "Doctor does not belong to the selected hospital.");
        }

        if (!patient.getHospital().getId().equals(hospital.getId())) {
            throw new BadRequestException(
                    "Patient does not belong to the selected hospital.");
        }
    }

    private String generateAppointmentCode() {
        return "APT-" + UUID.randomUUID().toString()
                .substring(0, 8)
                .toUpperCase();
    }
}