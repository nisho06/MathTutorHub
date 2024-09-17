package com.math.tutor.hub.year_and_topic_management.repository;

import com.math.tutor.hub.year_and_topic_management.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Integer> {

    Optional<Topic> findByTopicId(int topicId);
}
