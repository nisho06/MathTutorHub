package com.math.tutor.hub.tutoring_request_service.exceptions;

import com.math.tutor.hub.tutoring_request_service.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TutoringRequestNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleTutoringRequestNotFoundException(TutoringRequestNotFoundException ex){
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TopicNotExistForYearException.class)
    public ResponseEntity<ErrorResponse> handleTopicNotExistForYearException(TopicNotExistForYearException ex){
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
