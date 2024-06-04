package com.example.demo.repository;

import com.example.demo.model.DataBase.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminLoginRepository extends JpaRepository<Admin, String> { // ID의 타입을 String으로 변경
    Admin findByAdminPhoneNumberAndAdminPassword(String adminPhoneNumber, String adminPassword);
    Admin findByAdminPhoneNumber(String adminPhoneNumber);
    Admin findByAdminStoreCode(Integer adminStoreCode);
    boolean existsByAdminPhoneNumber(String adminPhoneNumber);


    @Modifying
    @Query("UPDATE Admin u SET u.adminPassword = :newPassword WHERE u.adminPhoneNumber = :phoneNumber")
    void updateAdminPassword(@Param("phoneNumber") String phoneNumber, @Param("newPassword") String newPassword);

}
