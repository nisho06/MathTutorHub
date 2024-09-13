package com.math.tutor.hub.tutor_management_service.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Builder
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TutorDTO {

    @NotBlank(message = "firstName cannot be empty.")
    private String firstName;
    @NotBlank(message = "surname cannot be empty.")
    private String surname;
    @NotBlank(message = "email cannot be empty.")
    private String email;
    @NotBlank(message = "phoneNumber cannot be empty.")
    private String phoneNumber;
    private String postcode;
    private Boolean isQualified = false;
    private Boolean isActive = true;
    private List<TutorYearsDTO> tutorYears;
}
