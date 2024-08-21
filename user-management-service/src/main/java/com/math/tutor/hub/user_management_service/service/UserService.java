package com.math.tutor.hub.user_management_service.service;

import com.math.tutor.hub.user_management_service.exception.UserNotFoundException;
import com.math.tutor.hub.user_management_service.model.User;
import com.math.tutor.hub.user_management_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(){
    }

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserByUserId(int userId){
        Optional<User> user =  userRepository.findUserByUserId(userId);

        if (user.isEmpty()){
            throw new UserNotFoundException("User not found for the user id:- " + userId);
        }
        return user.get();
    }

    public User getUserByEmail(String email){
        Optional<User> user = userRepository.findUserByEmail(email);

        if (user.isEmpty()){
            throw new UserNotFoundException("User not found for the email:- " + email);
        }
        return user.get();
    }
}
