package com.math.tutor.hub.tutoring_request_service.exceptions;

public class TopicNotExistForYearException extends RuntimeException{

    public TopicNotExistForYearException(String description){
        super(description);
    }
}
