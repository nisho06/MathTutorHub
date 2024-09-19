package com.math.tutor.hub.tutoring_request_service.dto;

import com.math.tutor.hub.tutoring_request_service.enums.ModeOfTeaching;
import com.math.tutor.hub.tutoring_request_service.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TutoringRequestResponseDTO {
    private int requestId;
    private String studentName;
    private int studentYear;
    private int userId;
    private String parentMobile;
    @Enumerated(value = EnumType.STRING)
    private ModeOfTeaching modeOfTeaching;
    private LocalDateTime requestDate;
    @Enumerated(value = EnumType.STRING)
    private Status status;
    private String additionalNotes;
    private List<String> topics;
}
