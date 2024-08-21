package com.math.tutor.hub.user_management_service.service;

import com.math.tutor.hub.user_management_service.dto.UserDTO;
import com.math.tutor.hub.user_management_service.exception.UserNotFoundException;
import com.math.tutor.hub.user_management_service.model.User;
import com.math.tutor.hub.user_management_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(){
    }

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO getUserByUserId(int userId){

        User user = userRepository.findUserByUserId(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found for the user id:- " + userId));
        return convertToDto(user);
    }

    public UserDTO getUserByEmail(String email){

        User user = userRepository.findUserByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found for the email:- " + email));
        return convertToDto(user);
    }

    public List<UserDTO> getAllUsers(){
        List<User> userList =  userRepository.findAll();
        List<UserDTO> userDTOList = new ArrayList<>();
        for (User user: userList){
            userDTOList.add(convertToDto(user));
        }
        return userDTOList;
    }

    public UserDTO convertToDto(User user){

        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(user.getUserId());
        userDTO.setEmail(user.getEmail());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setSurname(user.getSurname());
        userDTO.setPhoneNo(user.getPhoneNo());
        userDTO.setAddress(user.getAddress());
        userDTO.setPostcode(user.getPostcode());
        userDTO.setRole(user.getRole());
        userDTO.setSubscriptionTier(user.getSubscriptionTier());
        userDTO.setUserCreatedAt(user.getUserCreatedAt());
        userDTO.setPasswordLastChanged(user.getPasswordLastChanged());
        userDTO.setLastLoginAt(user.getLastLoginAt());
        return userDTO;
    }
}
