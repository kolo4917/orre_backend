package com.example.demo.repository;

import com.example.demo.model.DataBase.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSaveRepository extends JpaRepository<User, String> {
    boolean existsByToken(String token);
    boolean existsByPhoneNumber(String phoneNumber);
    void deleteByPhoneNumber(String phoneNumber);
    User findByPhoneNumber(String phoneNumber);



}
