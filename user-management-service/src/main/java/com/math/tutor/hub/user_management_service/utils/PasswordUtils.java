package com.math.tutor.hub.user_management_service.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class PasswordUtils {

    private static final int SALT_LENGTH = 16;

    public static String hashPassword(String password, String salt){
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(salt.getBytes());  // Update digest with salt
            byte[] hashedPasswordBytes = messageDigest.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(hashedPasswordBytes);  // Encode to Base64
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error occurred during password hashing", e);
        }
    }

    public static String generateRandomSalt(){
        SecureRandom secureRandom = new SecureRandom();
        byte[] salt = new byte[SALT_LENGTH];
        secureRandom.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);  // Encode salt to Base64
    }
}
