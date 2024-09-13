package com.math.tutor.hub.tutor_management_service.dto;

import com.math.tutor.hub.tutor_management_service.model.TutorYearsPK;
import lombok.*;

@Builder
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TutorYearsDTO {

    private int tutorYear;
}
