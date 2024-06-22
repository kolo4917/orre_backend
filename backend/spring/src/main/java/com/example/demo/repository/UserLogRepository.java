package com.example.demo.repository;

import com.example.demo.model.DataBase.UserLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserLogRepository extends JpaRepository<UserLog, Long> {

    @Query(value = "SELECT MAX(historyNum) FROM UserLog WHERE userPhoneNumber = :phoneNumber")
    Integer findLatestHistoryNumByUserPhoneNumber(String phoneNumber);

    UserLog findFirstByUserPhoneNumberAndStoreCodeOrderByHistoryNumDesc(String phoneNumber, int storeCode);
    UserLog findByUserPhoneNumberAndStoreCodeAndStatusStartingWith(String phoneNumber, int storeCode, String statusPrefix);

    UserLog findByUserPhoneNumberAndStatusAndStoreCode(String phoneNumber, String status, int storeCode); //순서 맞아야 함

    List<UserLog> findByUserPhoneNumber(String phoneNumber);

    List<UserLog> findByStoreCode(int storeCode);

}
