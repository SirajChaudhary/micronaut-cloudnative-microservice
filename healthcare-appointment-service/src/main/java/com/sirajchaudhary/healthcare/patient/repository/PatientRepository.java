package com.sirajchaudhary.healthcare.patient.repository;

import com.sirajchaudhary.healthcare.patient.entity.Patient;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    Optional<Patient> findByPatientCode(String patientCode);

    boolean existsByEmail(String email);

    Page<Patient> findAll(Pageable pageable);
}