package com.math.tutor.hub.user_management_service.controller;

import com.math.tutor.hub.user_management_service.model.User;
import com.math.tutor.hub.user_management_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = {"id/{userId}"})
    public ResponseEntity<?> getUserByUserId(@PathVariable int userId){

        User user = userService.getUserByUserId(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping(path = {"email/{email}"})
    public ResponseEntity<?> getUserByEmail(@PathVariable String email){

        User user = userService.getUserByEmail(email);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
