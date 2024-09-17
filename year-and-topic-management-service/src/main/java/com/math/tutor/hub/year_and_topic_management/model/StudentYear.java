package com.math.tutor.hub.year_and_topic_management.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "student_year")
public class StudentYear {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "year_id")
    private int yearId;
    @NotBlank(message = "yearLabel cannot be blank.")
    @Column(name = "year_label")
    private int yearLabel;
    @OneToMany(mappedBy = "studentYear", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    private List<YearTopic> yearTopicSet;
}
