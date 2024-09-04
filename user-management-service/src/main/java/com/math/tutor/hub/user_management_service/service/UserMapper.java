package com.math.tutor.hub.user_management_service.service;

import com.math.tutor.hub.user_management_service.dto.UserRequestDTO;
import com.math.tutor.hub.user_management_service.dto.UserResponseDTO;
import com.math.tutor.hub.user_management_service.model.User;
import com.math.tutor.hub.user_management_service.utils.PasswordUtils;

import java.time.LocalDateTime;

public class UserMapper {

    public User updateUserFromUserRequestDTO(UserRequestDTO userRequestDTO, User user){

        if (userRequestDTO.getEmail() != null){
            user.setEmail(userRequestDTO.getEmail());
        }
        if (userRequestDTO.getFirstName() != null) {
            user.setFirstName(userRequestDTO.getFirstName());
        }
        if (userRequestDTO.getSurname() != null) {
            user.setSurname(userRequestDTO.getSurname());
        }
        if (userRequestDTO.getPhoneNo() != null) {
            user.setPhoneNo(userRequestDTO.getPhoneNo());
        }
        if (userRequestDTO.getAddress() != null) {
            user.setAddress(userRequestDTO.getAddress());
        }
        if (userRequestDTO.getPostcode() != null) {
            user.setPostcode(userRequestDTO.getPostcode());
        }
        if (userRequestDTO.getRole() != null) {
            user.setRole(userRequestDTO.getRole());
        }
        if (userRequestDTO.getSubscriptionTier() != null) {
            user.setSubscriptionTier(userRequestDTO.getSubscriptionTier());
        }

        return user;
    }

    public static UserResponseDTO convertToUserResponseDto(User user){

        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setUserId(user.getUserId());
        userResponseDTO.setEmail(user.getEmail());
        userResponseDTO.setFirstName(user.getFirstName());
        userResponseDTO.setSurname(user.getSurname());
        userResponseDTO.setPhoneNo(user.getPhoneNo());
        userResponseDTO.setAddress(user.getAddress());
        userResponseDTO.setPostcode(user.getPostcode());
        userResponseDTO.setRole(user.getRole());
        userResponseDTO.setSubscriptionTier(user.getSubscriptionTier());
        userResponseDTO.setUserCreatedAt(user.getUserCreatedAt());
        userResponseDTO.setPasswordLastChanged(user.getPasswordLastChanged());
        userResponseDTO.setLastLoginAt(user.getLastLoginAt());
        return userResponseDTO;
    }

}
