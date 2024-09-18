package com.math.tutor.hub.year_and_topic_management.repository;

import com.math.tutor.hub.year_and_topic_management.model.YearTopic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface YearTopicRepository extends JpaRepository<YearTopic, Integer> {

    Optional<YearTopic> findByYearTopicId(int yearTopicId);

}
