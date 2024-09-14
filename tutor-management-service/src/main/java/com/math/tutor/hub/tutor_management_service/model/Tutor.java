package com.math.tutor.hub.tutor_management_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @Column(name = "tutor_id")
    private int tutorId;
    @NotBlank(message = "firstName cannot be empty.")
    @Column(name = "first_name")
    private String firstName;
    @NotBlank(message = "surname cannot be empty.")
    @Column(name = "surname")
    private String surname;
    @NotBlank(message = "email cannot be empty.")
    @Column(name = "email")
    private String email;
    @NotBlank(message = "phoneNo cannot be empty.")
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "postcode")
    private String postcode;
    @Column(name = "is_qualified")
    private Boolean isQualified = false;
    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();
    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();
    private Boolean isActive = true;
    // Mapped By - tutor property (the field on TutorYears) on the TutorYears is the owner of the relationship.
    @OneToMany(mappedBy = "tutor", fetch = FetchType.EAGER)
    private List<TutorYears> tutorYears ;
    @OneToMany(mappedBy = "tutor", fetch = FetchType.EAGER)
    private List<TutorsAvailability> tutorsAvailabilities;
}
