package com.math.tutor.hub.user_management_service.service;

import com.math.tutor.hub.user_management_service.dto.SignInRequest;
import com.math.tutor.hub.user_management_service.dto.UserRequestDTO;
import com.math.tutor.hub.user_management_service.enums.Role;
import com.math.tutor.hub.user_management_service.enums.SubscriptionTier;
import com.math.tutor.hub.user_management_service.exception.EmailAlreadyExistsException;
import com.math.tutor.hub.user_management_service.jwt.JwtService;
import com.math.tutor.hub.user_management_service.model.User;
import com.math.tutor.hub.user_management_service.repository.UserRepository;
import com.math.tutor.hub.user_management_service.response.AuthResponse;
import com.math.tutor.hub.user_management_service.security.CustomUserDetails;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthService {
    private PasswordEncoder passwordEncoder;
    private JwtService jwtService;
    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;

    public AuthResponse signUp(UserRequestDTO request) {
        if (request.getRole() == null){
            request.setRole(Role.STUDENT);
        }
        if (request.getSubscriptionTier() == null){
            request.setSubscriptionTier(SubscriptionTier.FREE);
        }
        Optional<User> userForValidation = userRepository.findUserByEmail(request.getEmail());
        if (userForValidation.isPresent()){
            throw new EmailAlreadyExistsException("User already exist for the email:- " + request.getEmail());
        }
        var user = User.builder().firstName(request.getFirstName()).surname(request.getSurname())
                .email(request.getEmail()).password(passwordEncoder.encode(request.getPassword()))
                .phoneNo(request.getPhoneNo()).address(request.getAddress()).postcode(request.getPostcode())
                .role(request.getRole()).subscriptionTier(request.getSubscriptionTier())
                .userCreatedAt(LocalDateTime.now()).passwordLastChanged(LocalDateTime.now()).build();
        userRepository.save(user);
        var jwt = jwtService.generateToken(new CustomUserDetails(user));
        return AuthResponse.builder().token(jwt).build();
    }

    public AuthResponse signIn(SignInRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = userRepository.findUserByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
        var jwt = jwtService.generateToken(new CustomUserDetails(user));
        return AuthResponse.builder().token(jwt).build();
    }
}
