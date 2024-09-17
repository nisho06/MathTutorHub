package com.math.tutor.hub.year_and_topic_management.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "year_topic")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class YearTopic {
    @Id
    @Column(name = "year_topic_id")
    private int yearTopicId;
    @Column(name = "year_id")
    private int yearId;
    @Column(name = "topic_id")
    private int topicId;
    @ManyToOne
    @JoinColumn(name = "year_id", insertable = false, updatable = false)
    private StudentYear studentYear;
    @ManyToOne
    @JoinColumn(name = "topic_id", insertable = false, updatable = false)
    private Topic topic;
}
