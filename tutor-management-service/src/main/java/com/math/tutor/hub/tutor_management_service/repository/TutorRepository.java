package com.math.tutor.hub.tutor_management_service.repository;

import com.math.tutor.hub.tutor_management_service.model.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface TutorRepository extends JpaRepository<Tutor, Integer> {
    Optional<Tutor> findTutorByTutorId(int tutorId);
    Optional<Tutor> findTutorByEmail(String email);
    boolean existsByEmail(String email);
    @Transactional
    void deleteByEmail(String email);

    @Query("SELECT t FROM Tutor t WHERE " +
            "(:firstName is NULL OR t.firstName = :firstName) AND " +
            "(:surname is NULL OR t.surname = :surname) AND " +
            "(:postcode is NULL OR t.postcode = :postcode) AND " +
            "(:phoneNumber is NULL OR t.phoneNumber = :phoneNumber) AND " +
            "(:isQualified is NULL OR t.isQualified = :isQualified) AND " +
            "(:isActive is NULL OR t.isActive = :isActive)")
    List<Tutor> filterTutors(
            @Param("firstName") String firstName,
            @Param("surname") String surname,
            @Param("postcode") String postcode,
            @Param("phoneNumber") String phoneNumber,
            @Param("isQualified") Boolean isQualified,
            @Param("isActive") Boolean isActive
    );
}
