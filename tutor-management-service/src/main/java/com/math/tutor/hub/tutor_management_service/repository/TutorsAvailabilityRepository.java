package com.math.tutor.hub.tutor_management_service.repository;

import com.math.tutor.hub.tutor_management_service.model.TutorYears;
import com.math.tutor.hub.tutor_management_service.model.TutorsAvailability;
import com.math.tutor.hub.tutor_management_service.model.TutorsAvailabilityPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TutorsAvailabilityRepository extends JpaRepository<TutorsAvailability, TutorsAvailabilityPK> {

    @Query("SELECT ta FROM TutorsAvailability ta WHERE ta.tutorsAvailabilityPK.tutorId = :tutorId")
    List<TutorsAvailability> findTutorsAvailabilityByTutorId(@Param("tutorId") int tutorId);
}
