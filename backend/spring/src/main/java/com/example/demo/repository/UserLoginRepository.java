package com.example.demo.repository;

import com.example.demo.model.DataBase.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLoginRepository extends JpaRepository<User, String> { // ID의 타입을 String으로 변경
    User findByPhoneNumberAndPassword(String phoneNumber, String password);
}
