package com.math.tutor.hub.user_management_service.service;

import com.math.tutor.hub.user_management_service.dto.UserRequestDTO;
import com.math.tutor.hub.user_management_service.dto.UserResponseDTO;
import com.math.tutor.hub.user_management_service.enums.Role;
import com.math.tutor.hub.user_management_service.enums.SubscriptionTier;
import com.math.tutor.hub.user_management_service.exception.ForbiddenAccessException;
import com.math.tutor.hub.user_management_service.exception.UnauthorizedAccessException;
import com.math.tutor.hub.user_management_service.exception.UserNotFoundException;
import com.math.tutor.hub.user_management_service.model.User;
import com.math.tutor.hub.user_management_service.repository.UserRepository;
import com.math.tutor.hub.user_management_service.security.CustomUserDetails;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class UserService {

    private static final SubscriptionTier DEFAULT_SUBSCRIPTION_TIER = SubscriptionTier.FREE;

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

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
         return convertUserToUserResponseDTOList(userList);
    }

    public UserResponseDTO updateUserByUserId(String email, UserRequestDTO userRequestDTO) {
        User user = userRepository.findUserByEmail(email).orElseThrow(()->
                new UserNotFoundException("User not found for the email:- " + email));

        authenticateRequest(user.getEmail());

        String firstName = userRequestDTO.getFirstName();
        String surname = userRequestDTO.getSurname();
        String phoneNo = userRequestDTO.getPhoneNo();
        String address = userRequestDTO.getAddress();
        String postcode = userRequestDTO.getPostcode();
        String password = userRequestDTO.getPassword();
        Role role = userRequestDTO.getRole();
        SubscriptionTier subscriptionTier = userRequestDTO.getSubscriptionTier();

        if (firstName != null && !Objects.equals(firstName, user.getFirstName())){
            user.setFirstName(firstName);
        }

        if (surname != null && !Objects.equals(surname, user.getSurname())){
            user.setSurname(surname);
        }

        if (phoneNo != null && !Objects.equals(phoneNo, user.getPhoneNo())){
            user.setPhoneNo(phoneNo);
        }

        if (address != null && !Objects.equals(address, user.getAddress())){
            user.setAddress(address);
        }

        if (postcode != null && !Objects.equals(postcode, user.getPostcode())){
            user.setPostcode(postcode);
        }

        if (password != null){
            user.setPassword(passwordEncoder.encode(password));
        }

        if (role != null && !Objects.equals(role, user.getRole())){
            user.setRole(role);
        }

        if (subscriptionTier != null && !Objects.equals(subscriptionTier, user.getSubscriptionTier())){
            user.setSubscriptionTier(subscriptionTier);
        }

        userRepository.save(user);
        return UserMapper.convertToUserResponseDto(user);
    }

    public void deleteUserByEmail(String email){
        authenticateRequest(email);
        if (userRepository.existsByEmail(email)){
            userRepository.deleteByEmail(email);
        } else {
            throw new UserNotFoundException("User not found for the email:- " + email);
        }
    }

    public List<UserResponseDTO> filterUsersByParameter(String email, String firstName, String surname, String postcode,
                                                        String phoneNo, Role role, SubscriptionTier subscriptionTier) {
        if (email == null && firstName == null && surname == null
                && postcode == null && phoneNo == null && role == null && subscriptionTier == null){
            return getAllUsers();
        } else {
            authenticateRequest(null);
            List<User> filteredUserList = userRepository.filterUsers(email, firstName, surname, postcode, phoneNo,
                    role, subscriptionTier);
            return convertUserToUserResponseDTOList(filteredUserList);
        }
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

    private List<UserResponseDTO> convertUserToUserResponseDTOList(List<User> userList){
        List<UserResponseDTO> userResponseDTOList = new ArrayList<>();
        for (User user: userList){
            userResponseDTOList.add(UserMapper.convertToUserResponseDto(user));
        }
        return userResponseDTOList;
    }
}
