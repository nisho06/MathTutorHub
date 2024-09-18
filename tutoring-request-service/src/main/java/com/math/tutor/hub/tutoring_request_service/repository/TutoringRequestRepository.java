package com.math.tutor.hub.tutoring_request_service.repository;

import com.math.tutor.hub.tutoring_request_service.model.TutoringRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TutoringRequestRepository extends JpaRepository<TutoringRequest, Integer> {
    Optional<TutoringRequest> findByRequestId(int requestId);
}
