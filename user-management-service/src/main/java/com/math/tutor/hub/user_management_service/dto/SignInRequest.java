package com.math.tutor.hub.user_management_service.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignInRequest {
    @NotBlank(message = "email cannot be blank.")
    private String email;
    @NotBlank(message = "password cannot be blank.")
    private String password;
}
