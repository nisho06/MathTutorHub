package com.math.tutor.hub.tutoring_request_service.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter
@Setter
public class ErrorResponse {
    private String timestamp;
    private int status;
    private String error;
    private String description;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<String> details;

    public ErrorResponse(HttpStatus httpStatus, String description) {
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.status = httpStatus.value();
        this.error = httpStatus.name();
        this.description = description;
    }

    public ErrorResponse(HttpStatus httpStatus, List<String> details, String description) {
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.status = httpStatus.value();
        this.error = httpStatus.name();
        this.description = description;
        this.details = details;
    }
}