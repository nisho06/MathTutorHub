package com.math.tutor.hub.user_management_service.repository;

import com.math.tutor.hub.user_management_service.enums.Role;
import com.math.tutor.hub.user_management_service.enums.SubscriptionTier;
import com.math.tutor.hub.user_management_service.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findUserByUserId(int userId);
    Optional<User> findUserByEmail(String email);
    boolean existsByEmail(String email);
    @Transactional
    void deleteByEmail(String email);

    @Query("SELECT u FROM User u WHERE " +
            "(:email IS NULL OR u.email = :email) AND " +
            "(:firstName IS NULL OR u.firstName = :firstName) AND " +
            "(:surname IS NULL OR u.surname = :surname) AND " +
            "(:postcode IS NULL OR u.postcode = :postcode) AND " +
            "(:phoneNo IS NULL OR u.phoneNo = :phoneNo) AND " +
            "(:role IS NULL OR u.role = :role) AND " +
            "(:subscriptionTier IS NULL OR u.subscriptionTier = :subscriptionTier)")
    List<User> filterUsers(
            @Param("email") String email,
            @Param("firstName") String firstName,
            @Param("surname") String surname,
            @Param("postcode") String postcode,
            @Param("phoneNo") String phoneNo,
            @Param("role") Role role,
            @Param("subscriptionTier") SubscriptionTier subscriptionTier);
}
