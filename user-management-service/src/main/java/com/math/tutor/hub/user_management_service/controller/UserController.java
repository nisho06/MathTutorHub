package com.math.tutor.hub.user_management_service.controller;

import com.math.tutor.hub.user_management_service.dto.UserResponseDTO;
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
}
