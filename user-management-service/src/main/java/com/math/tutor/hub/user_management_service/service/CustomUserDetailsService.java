package com.math.tutor.hub.user_management_service.service;

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
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(username).orElseThrow(
                ()-> new UsernameNotFoundException("User not found"));
        return new CustomUserDetails(user);
    }
}
