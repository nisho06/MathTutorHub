package com.math.tutor.hub.user_management_service.exception;

public class UnauthorizedAccessException extends RuntimeException{
    public UnauthorizedAccessException(String description){
        super(description);
    }
}
