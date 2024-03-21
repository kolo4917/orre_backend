package com.example.demo.repository;

import com.example.demo.model.DataBase.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSaveRepository extends JpaRepository<User, String> {
    // phoneNumber를 기본 키로 사용하기 때문에, ID의 타입은 String입니다.
}
