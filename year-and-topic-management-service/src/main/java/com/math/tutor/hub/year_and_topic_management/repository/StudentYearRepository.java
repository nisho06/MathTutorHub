package com.math.tutor.hub.year_and_topic_management.repository;

import com.math.tutor.hub.year_and_topic_management.model.StudentYear;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentYearRepository extends JpaRepository<StudentYear, Integer> {

    Optional<StudentYear> findByYearLabel(int yearLabel);

    Optional<StudentYear> findByYearId(int yearId);
}
