package com.math.tutor.hub.user_management_service.repository;

import com.math.tutor.hub.user_management_service.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findUserByUserId(int userId);
    Optional<User> findUserByEmail(String email);
    boolean existsByEmail(String email);
    @Transactional
    void deleteByEmail(String email);
}
