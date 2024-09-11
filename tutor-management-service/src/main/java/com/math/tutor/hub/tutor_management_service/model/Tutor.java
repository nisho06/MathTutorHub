package com.math.tutor.hub.tutor_management_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tutor")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Tutor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tutorId;
    @NotBlank(message = "firstName cannot be empty.")
    private String firstName;
    @NotBlank(message = "surname cannot be empty.")
    private String surname;
    @NotBlank(message = "email cannot be empty.")
    private String email;
    @NotBlank(message = "phoneNo cannot be empty.")
    private String phoneNumber;
    private String postcode;
    private Boolean isQualified = false;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();
    private Boolean isActive = true;
//    @OneToMany(mappedBy = "tutor")
//    private Set<TutorYears> tutorYears;;

}
