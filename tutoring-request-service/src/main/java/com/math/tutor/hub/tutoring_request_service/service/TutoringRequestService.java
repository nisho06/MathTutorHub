package com.math.tutor.hub.tutoring_request_service.service;

import com.math.tutor.hub.tutoring_request_service.exceptions.TutoringRequestNotFoundException;
import com.math.tutor.hub.tutoring_request_service.model.TutoringRequest;
import com.math.tutor.hub.tutoring_request_service.repository.TutoringRequestRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Data
@AllArgsConstructor
@Service
public class TutoringRequestService {

    private TutoringRequestRepository tutoringRequestRepository;

    public TutoringRequest getTutoringRequestForId(int requestId) {
        return tutoringRequestRepository.findByRequestId(requestId).orElseThrow(
                () -> new TutoringRequestNotFoundException("Tutoring Request not found for the id:- " + requestId));
    }
}
