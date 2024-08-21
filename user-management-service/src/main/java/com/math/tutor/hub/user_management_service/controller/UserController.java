package com.math.tutor.hub.user_management_service.controller;

import com.math.tutor.hub.user_management_service.dto.UserDTO;
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
        List<UserDTO> userDTOList = userService.getAllUsers();
        return new ResponseEntity<>(userDTOList, HttpStatus.OK);
    }

    @GetMapping(path = {"id/{userId}"})
    public ResponseEntity<?> getUserByUserId(@PathVariable int userId){

        UserDTO userDTO = userService.getUserByUserId(userId);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @GetMapping(path = {"email/{email}"})
    public ResponseEntity<?> getUserByEmail(@PathVariable String email){

        UserDTO userDTO = userService.getUserByEmail(email);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }
}
