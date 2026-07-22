package com.sirajchaudhary.healthcare.hospital.repository;

import com.sirajchaudhary.healthcare.hospital.entity.Hospital;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;

import java.util.Optional;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Long> {

    Optional<Hospital> findByHospitalCode(String hospitalCode);

    boolean existsByRegistrationNumber(String registrationNumber);

    boolean existsByEmail(String email);

    Page<Hospital> findAll(Pageable pageable);
}