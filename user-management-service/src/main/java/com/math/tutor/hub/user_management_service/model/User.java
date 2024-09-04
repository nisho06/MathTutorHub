package com.math.tutor.hub.user_management_service.model;

import com.math.tutor.hub.user_management_service.enums.Role;
import com.math.tutor.hub.user_management_service.enums.SubscriptionTier;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "user")
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // IDENTITY Generation type is used when the database table  itself handles the auto increment of the primary key.
    private int userId;
    @NotBlank(message = "email cannot be empty.")
    @Email(message = "Email should be valid.")
    private String email;
    @NotBlank(message = "firstName cannot be empty.")
    private String firstName;
    @NotBlank(message = "surname cannot be empty.")
    private String surname;
    @NotBlank(message = "phoneNo cannot be empty.")
    private String phoneNo;
    private String address;
    private String postcode;
    @Enumerated(value = EnumType.STRING)
    private Role role;
    @NotBlank(message = "password cannot be empty.")
    private String password;
    @Enumerated(value = EnumType.STRING)
    private SubscriptionTier subscriptionTier;
    private LocalDateTime userCreatedAt;
    private LocalDateTime passwordLastChanged;
    private LocalDateTime lastLoginAt;

    public User(String email, String firstName, String surname, String phoneNo, String address, String postcode,
                Role role, String password, SubscriptionTier subscriptionTier,
                LocalDateTime userCreatedAt, LocalDateTime passwordLastChanged, LocalDateTime lastLoginAt) {
        this.email = email;
        this.firstName = firstName;
        this.surname = surname;
        this.phoneNo = phoneNo;
        this.address = address;
        this.postcode = postcode;
        this.role = role;
        this.password = password;
        this.subscriptionTier = subscriptionTier;
        this.userCreatedAt = userCreatedAt;
        this.passwordLastChanged = passwordLastChanged;
        this.lastLoginAt = lastLoginAt;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public SubscriptionTier getSubscriptionTier() {
        return subscriptionTier;
    }

    public void setSubscriptionTier(SubscriptionTier subscriptionTier) {
        this.subscriptionTier = subscriptionTier;
    }

    public LocalDateTime getUserCreatedAt() {
        return userCreatedAt;
    }

    public void setUserCreatedAt(LocalDateTime userCreatedAt) {
        this.userCreatedAt = userCreatedAt;
    }

    public LocalDateTime getPasswordLastChanged() {
        return passwordLastChanged;
    }

    public void setPasswordLastChanged(LocalDateTime passwordLastChanged) {
        this.passwordLastChanged = passwordLastChanged;
    }

    public LocalDateTime getLastLoginAt() {
        return lastLoginAt;
    }

    public void setLastLoginAt(LocalDateTime lastLoginAt) {
        this.lastLoginAt = lastLoginAt;
    }
}
