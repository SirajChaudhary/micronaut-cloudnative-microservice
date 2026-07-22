package com.sirajchaudhary.healthcare.appointment.repository;

import com.sirajchaudhary.healthcare.appointment.entity.Appointment;
import com.sirajchaudhary.healthcare.doctor.entity.Doctor;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    Optional<Appointment> findByAppointmentCode(String appointmentCode);

    Page<Appointment> findAll(Pageable pageable);

    boolean existsByDoctorAndAppointmentDateTime(
            Doctor doctor,
            LocalDateTime appointmentDateTime);
}