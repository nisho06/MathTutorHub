package com.math.tutor.hub.tutor_management_service.model;

import com.math.tutor.hub.tutor_management_service.enums.DayOfWeek;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TutorsAvailabilityPK implements Serializable {
    @Column(name = "tutor_id")
    private int tutorId;
    @Column(name = "day_of_week")
    @Enumerated(value = EnumType.STRING)
    private DayOfWeek dayOfWeek;

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        TutorsAvailabilityPK that = (TutorsAvailabilityPK) o;
        return tutorId == that.tutorId && dayOfWeek == that.dayOfWeek;
    }

    @Override
    public int hashCode() {
        return Objects.hash(tutorId, dayOfWeek);
    }
}
