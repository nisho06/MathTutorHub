package com.math.tutor.hub.user_management_service.exception;

public class EmailAlreadyExistsException extends RuntimeException{

    public EmailAlreadyExistsException(String description) {
        super(description);
    }
}
