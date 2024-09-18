package com.math.tutor.hub.tutoring_request_service.controller;

import com.math.tutor.hub.tutoring_request_service.model.TutoringRequest;
import com.math.tutor.hub.tutoring_request_service.service.TutoringRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/tutoring-request")
public class TutoringRequestController {

    private TutoringRequestService tutoringRequestService;

    @Autowired
    public TutoringRequestController(TutoringRequestService tutoringRequestService){
        this.tutoringRequestService = tutoringRequestService;
    }

    @GetMapping(path = "/{requestId}")
    public ResponseEntity<?> getTutoringRequestForId(@PathVariable int requestId){
        TutoringRequest tutoringRequest = tutoringRequestService.getTutoringRequestForId(requestId);
        return new ResponseEntity<>(tutoringRequest, HttpStatus.OK);
    }
}
