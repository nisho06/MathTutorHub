package com.math.tutor.hub.tutor_management_service.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tutors_availability")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class TutorsAvailability {
    @EmbeddedId
    private TutorsAvailabilityPK tutorsAvailabilityPK;
    @ManyToOne
    @JoinColumn(name = "tutor_id", insertable = false, updatable = false)
    private Tutor tutor;

}
