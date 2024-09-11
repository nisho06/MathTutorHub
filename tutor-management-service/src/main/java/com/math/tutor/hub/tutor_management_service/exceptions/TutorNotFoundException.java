package com.math.tutor.hub.tutor_management_service.exceptions;

public class TutorNotFoundException extends RuntimeException{

    public TutorNotFoundException(String description) {
        super(description);
    }
}
