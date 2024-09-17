package com.math.tutor.hub.year_and_topic_management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopicsForStudentYear {
    private int yearId;
    private int year;
    private List<String> topics;
}
