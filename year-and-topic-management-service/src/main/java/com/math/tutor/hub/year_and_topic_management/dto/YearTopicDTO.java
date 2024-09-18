package com.math.tutor.hub.year_and_topic_management.dto;

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
