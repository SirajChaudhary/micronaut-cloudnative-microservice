package com.sirajchaudhary.healthcare.doctor.repository;

import com.sirajchaudhary.healthcare.doctor.entity.Doctor;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import jakarta.annotation.Nullable;

import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    Optional<Doctor> findByDoctorCode(String doctorCode);

    boolean existsByEmail(String email);

    Page<Doctor> findAll(Pageable pageable);

    /**
     * =========================================================================
     * Advanced Search Query
     * =========================================================================
     *
     * This JPQL query demonstrates advanced search capabilities using
     * Micronaut Data JPA.
     *
     * Features:
     * • JPQL (Java Persistence Query Language) with entity relationships
     * • JOIN FETCH between Doctor and Hospital entities
     * • Eliminates LazyInitializationException by eagerly loading Hospital
     * • Dynamic filtering using optional query parameters
     * • Case-insensitive keyword search using LOWER() and LIKE
     * • Exact match filters
     * • Range filters using >= operators
     * • Automatic pagination and sorting via Pageable
     * • Separate count query for efficient pagination
     *
     * Search Parameters:
     * • q               -> Partial search across firstName, lastName,
     *                      specialization and email
     * • hospitalCode    -> Filter by hospital
     * • specialization  -> Filter by specialization
     * • minExperience   -> Experience >= value
     * • minRating       -> Rating >= value
     * • active          -> Active/Inactive doctors
     *
     * NOTE:
     * • JOIN FETCH eagerly loads the associated Hospital entity to prevent
     *   LazyInitializationException ("Could not initialize proxy - no session")
     *   while mapping Doctor entities to response DTOs.
     *
     * • The countQuery intentionally uses a normal JOIN instead of JOIN FETCH,
     *   because FETCH joins are not supported in COUNT queries.
     *
     * • Each filter is optional. When a parameter is null, its corresponding
     *   condition is ignored, allowing a single query to support many different
     *   search combinations.
     * =========================================================================
     */
    @Query(
            value = """
            SELECT doctor_
            FROM Doctor doctor_
            JOIN FETCH doctor_.hospital hospital_
            WHERE
                (:q IS NULL
                    OR LOWER(doctor_.firstName) LIKE :q
                    OR LOWER(doctor_.lastName) LIKE :q
                    OR LOWER(doctor_.specialization) LIKE :q
                    OR LOWER(doctor_.email) LIKE :q)
            AND (:hospitalCode IS NULL
                    OR hospital_.hospitalCode = :hospitalCode)
            AND (:specialization IS NULL
                    OR LOWER(doctor_.specialization) = :specialization)
            AND (:minExperience IS NULL
                    OR doctor_.experience >= :minExperience)
            AND (:minRating IS NULL
                    OR doctor_.rating >= :minRating)
            AND (:active IS NULL
                    OR doctor_.active = :active)
            """,
            countQuery = """
            SELECT COUNT(doctor_)
            FROM Doctor doctor_
            JOIN doctor_.hospital hospital_
            WHERE
                (:q IS NULL
                    OR LOWER(doctor_.firstName) LIKE :q
                    OR LOWER(doctor_.lastName) LIKE :q
                    OR LOWER(doctor_.specialization) LIKE :q
                    OR LOWER(doctor_.email) LIKE :q)
            AND (:hospitalCode IS NULL
                    OR hospital_.hospitalCode = :hospitalCode)
            AND (:specialization IS NULL
                    OR LOWER(doctor_.specialization) = :specialization)
            AND (:minExperience IS NULL
                    OR doctor_.experience >= :minExperience)
            AND (:minRating IS NULL
                    OR doctor_.rating >= :minRating)
            AND (:active IS NULL
                    OR doctor_.active = :active)
            """
    )
    Page<Doctor> searchDoctors(
            @Nullable String q,
            @Nullable String hospitalCode,
            @Nullable String specialization,
            @Nullable Integer minExperience,
            @Nullable Double minRating,
            @Nullable Boolean active,
            Pageable pageable
    );
}