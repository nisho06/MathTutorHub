package com.math.tutor.hub.user_management_service.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String description){
        super(description);
    }
}
