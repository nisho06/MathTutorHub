package com.math.tutor.hub.user_management_service.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<String> getDetails() {
        return details;
    }

    public void setDetails(List<String> details) {
        this.details = details;
    }
}
