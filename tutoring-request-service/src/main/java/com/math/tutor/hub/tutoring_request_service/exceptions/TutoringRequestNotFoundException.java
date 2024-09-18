package com.math.tutor.hub.tutoring_request_service.exceptions;

public class TutoringRequestNotFoundException extends RuntimeException{

    public TutoringRequestNotFoundException(String description){
        super(description);
    }
}
