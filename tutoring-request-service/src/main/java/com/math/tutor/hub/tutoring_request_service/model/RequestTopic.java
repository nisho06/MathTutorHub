package com.math.tutor.hub.tutoring_request_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Table(name = "request_topic")
@Entity
public class RequestTopic {
    @EmbeddedId
    private RequestTopicPK requestTopicPK;
    @ManyToOne
    @JoinColumn(name = "request_id", insertable = false, updatable = false)
    private TutoringRequest tutoringRequest;
}
