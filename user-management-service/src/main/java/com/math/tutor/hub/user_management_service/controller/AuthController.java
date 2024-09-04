package com.math.tutor.hub.user_management_service.controller;

import com.math.tutor.hub.user_management_service.dto.SignInRequest;
import com.math.tutor.hub.user_management_service.dto.UserRequestDTO;
import com.math.tutor.hub.user_management_service.response.AuthResponse;
import com.math.tutor.hub.user_management_service.service.AuthService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class AuthController {

    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signUp(@RequestBody UserRequestDTO userSignupRequest){
        return ResponseEntity.ok(authService.signUp(userSignupRequest));
    }

    @PostMapping("/signIn")
    public ResponseEntity<AuthResponse> signIn(@Valid @RequestBody SignInRequest signInRequest){
        return ResponseEntity.ok(authService.signIn(signInRequest));
    }
}
