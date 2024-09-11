package com.math.tutor.hub.tutor_management_service.exceptions;

public class TutorAlreadyExistException extends RuntimeException{
    public TutorAlreadyExistException(String description){
        super(description);
    }
}
