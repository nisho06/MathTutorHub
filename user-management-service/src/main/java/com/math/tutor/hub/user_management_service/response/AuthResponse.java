package com.math.tutor.hub.user_management_service.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class AuthResponse {
    private String token;
}
