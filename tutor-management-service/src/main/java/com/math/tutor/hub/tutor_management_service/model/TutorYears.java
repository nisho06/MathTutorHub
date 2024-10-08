package com.math.tutor.hub.tutor_management_service.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tutor_years")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class TutorYears {

    @EmbeddedId
    private TutorYearsPK tutorYearsPK;
    @ManyToOne
    @JoinColumn(name = "tutor_id", insertable = false, updatable = false)
    private Tutor tutor;
}
