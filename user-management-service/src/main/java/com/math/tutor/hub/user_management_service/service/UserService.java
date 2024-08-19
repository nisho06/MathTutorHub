package com.math.tutor.hub.user_management_service.service;

import org.apache.catalina.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public UserService(){

    }

    public String getUser(){
        return "This is sample user.";
    }
}
