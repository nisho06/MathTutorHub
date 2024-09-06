package com.math.tutor.hub.user_management_service.service;

import com.math.tutor.hub.user_management_service.exception.UserNotFoundException;
import com.math.tutor.hub.user_management_service.model.User;
import com.math.tutor.hub.user_management_service.repository.UserRepository;
import com.math.tutor.hub.user_management_service.security.CustomUserDetails;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UserNotFoundException {
        User user = userRepository.findUserByEmail(email).orElseThrow(
                ()-> new UserNotFoundException("User not found for the email:- " + email));
        return new CustomUserDetails(user);
    }
}
