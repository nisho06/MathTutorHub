package com.math.tutor.hub.user_management_service.service;

import com.math.tutor.hub.user_management_service.dto.UserResponseDTO;
import com.math.tutor.hub.user_management_service.enums.Role;
import com.math.tutor.hub.user_management_service.enums.SubscriptionTier;
import com.math.tutor.hub.user_management_service.exception.ForbiddenAccessException;
import com.math.tutor.hub.user_management_service.exception.UnauthorizedAccessException;
import com.math.tutor.hub.user_management_service.exception.UserNotFoundException;
import com.math.tutor.hub.user_management_service.model.User;
import com.math.tutor.hub.user_management_service.repository.UserRepository;
import com.math.tutor.hub.user_management_service.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private static final SubscriptionTier DEFAULT_SUBSCRIPTION_TIER = SubscriptionTier.FREE;

    private UserRepository userRepository;

    public UserService(){
    }

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponseDTO getUserByUserId(int userId){

        User user = userRepository.findUserByUserId(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found for the user id:- " + userId));
        authenticateRequest(user.getEmail());
        return UserMapper.convertToUserResponseDto(user);
    }

    public UserResponseDTO getUserByEmail(String email){

        authenticateRequest(email);

        User user = userRepository.findUserByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found for the email:- " + email));
        return UserMapper.convertToUserResponseDto(user);
    }

    public List<UserResponseDTO> getAllUsers(){

        authenticateRequest(null);

        List<User> userList =  userRepository.findAll();
        List<UserResponseDTO> userResponseDTOList = new ArrayList<>();
        for (User user: userList){
            userResponseDTOList.add(UserMapper.convertToUserResponseDto(user));
        }
        return userResponseDTOList;
    }

    private void authenticateRequest(String email){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !(authentication.getPrincipal() instanceof UserDetails)) {
            throw new UnauthorizedAccessException("Unauthorized access.");
        }

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        String authenticatedEmail = userDetails.getUsername();

        User authenticatedUser = userDetails.getUser();
        if ((!authenticatedEmail.equals(email)) && !(authenticatedUser.getRole().equals(Role.ADMIN))){
            throw new ForbiddenAccessException("Access Denied - You are not authorized to access this resource.");
        }
    }
}
