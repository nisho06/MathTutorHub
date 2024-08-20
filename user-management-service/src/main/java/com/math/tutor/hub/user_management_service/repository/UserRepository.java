package com.math.tutor.hub.user_management_service.repository;

import com.math.tutor.hub.user_management_service.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findUserByUserId(int userId);
    Optional<User> findUserByEmail(String email);
}
