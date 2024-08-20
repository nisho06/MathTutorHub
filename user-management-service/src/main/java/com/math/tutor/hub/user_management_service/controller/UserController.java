package com.math.tutor.hub.user_management_service.controller;

import com.math.tutor.hub.user_management_service.error.ErrorResponse;
import com.math.tutor.hub.user_management_service.model.User;
import com.math.tutor.hub.user_management_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

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
        try{
            User user = userService.getUserByUserId(userId);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (NoSuchElementException e){
            ErrorResponse errorResponse =
                    new ErrorResponse(HttpStatus.NOT_FOUND, "User not found for the user id:- " + userId);
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping(path = {"email/{email}"})
    public ResponseEntity<?> getUserByEmail(@PathVariable String email){
        try{
            User user = userService.getUserByEmail(email);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (NoSuchElementException e){
            ErrorResponse errorResponse =
                    new ErrorResponse(HttpStatus.NOT_FOUND, "User not found for the email:- " + email);
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }
    }
}
