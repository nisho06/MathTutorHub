package com.math.tutor.hub.tutoring_request_service.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.math.tutor.hub.tutoring_request_service.enums.ModeOfTeaching;
import com.math.tutor.hub.tutoring_request_service.enums.Status;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "tutoring_request")
public class TutoringRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "request_id")
    private int requestId;
    @Column(name = "student_name")
    private String studentName;
    @Column(name = "student_year_id")
    private int studentYearId;
    @Column(name = "user_id")
    private int userId;
    @Column(name = "parent_mobile")
    private String parentMobile;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "mode_of_teaching")
    private ModeOfTeaching modeOfTeaching;
    @Column(name = "request_date")
    private LocalDateTime requestDate;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
    private Status status;
    @Column(name = "additionalNotes")
    private String additionalNotes;
    @OneToMany(mappedBy = "tutoringRequest", fetch = FetchType.LAZY)
    @JsonBackReference
    private List<RequestTopic> requestTopics;
}
