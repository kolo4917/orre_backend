package com.example.demo.repository;

import com.example.demo.model.DataBase.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserSaveRepository extends JpaRepository<User, String> {
    boolean existsByToken(String token);
    boolean existsByPhoneNumber(String phoneNumber);
    void deleteByPhoneNumber(String phoneNumber);
    User findByPhoneNumber(String phoneNumber);

    @Modifying
    @Query("UPDATE User u SET u.password = :newPassword WHERE u.phoneNumber = :phoneNumber")
    void updateUserPassword(@Param("phoneNumber") String phoneNumber, @Param("newPassword") String newPassword);




}
