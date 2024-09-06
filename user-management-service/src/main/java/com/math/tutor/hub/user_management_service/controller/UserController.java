package com.math.tutor.hub.user_management_service.controller;

import com.math.tutor.hub.user_management_service.dto.UserRequestDTO;
import com.math.tutor.hub.user_management_service.dto.UserResponseDTO;
import com.math.tutor.hub.user_management_service.enums.Role;
import com.math.tutor.hub.user_management_service.enums.SubscriptionTier;
import com.math.tutor.hub.user_management_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers(){
        List<UserResponseDTO> userResponseDTOList = userService.getAllUsers();
        return new ResponseEntity<>(userResponseDTOList, HttpStatus.OK);
    }

    @GetMapping(path = {"id/{userId}"})
    public ResponseEntity<?> getUserByUserId(@PathVariable int userId){
        UserResponseDTO userResponseDTO = userService.getUserByUserId(userId);
        return new ResponseEntity<>(userResponseDTO, HttpStatus.OK);
    }

    @GetMapping(path = {"email/{email}"})
    public ResponseEntity<?> getUserByEmail(@PathVariable String email){
        UserResponseDTO userResponseDTO = userService.getUserByEmail(email);
        return new ResponseEntity<>(userResponseDTO, HttpStatus.OK);
    }

    @PutMapping(path = {"email/{email}"})
    public ResponseEntity<?> updateUserByEmail(@PathVariable String email, @RequestBody UserRequestDTO userRequestDTO){
        UserResponseDTO userResponseDTO = userService.updateUserByUserId(email, userRequestDTO);
        return new ResponseEntity<>(userResponseDTO, HttpStatus.OK);
    }

    @DeleteMapping(path = {"email/{email}"})
    public ResponseEntity<?> deleteUserByEmail(@PathVariable String email){
        userService.deleteUserByEmail(email);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(path = "/filter")
    public ResponseEntity<?> filterUsersByParameter(
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String surname,
            @RequestParam(required = false) String postcode,
            @RequestParam(required = false) String phoneNo,
            @RequestParam(required = false) Role role,
            @RequestParam(required = false)SubscriptionTier subscriptionTier){
        List<UserResponseDTO> filteredUserResponse = userService.filterUsersByParameter(email, firstName, surname,
                postcode, phoneNo, role, subscriptionTier);
        return new ResponseEntity<>(filteredUserResponse, HttpStatus.OK);
    }
}
