package com.math.tutor.hub.tutor_management_service.repository;

import com.math.tutor.hub.tutor_management_service.model.TutorYears;
import com.math.tutor.hub.tutor_management_service.model.TutorYearsPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TutorYearsRepository extends JpaRepository<TutorYears, TutorYearsPK> {

    @Query("SELECT ty FROM TutorYears ty WHERE ty.tutorYearsPK.tutorId = :tutorId")
    List<TutorYears> findTutorYearsByTutorId(@Param("tutorId") int tutorId);
}
