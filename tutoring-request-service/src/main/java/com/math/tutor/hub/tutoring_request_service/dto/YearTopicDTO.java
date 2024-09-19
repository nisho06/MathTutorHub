package com.math.tutor.hub.tutoring_request_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class YearTopicDTO {
    private int yearTopicId;
    private StudentYearResponseDTO studentYear;
    private TopicResponseDTO topic;
}
