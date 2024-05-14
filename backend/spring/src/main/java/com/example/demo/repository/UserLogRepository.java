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

    UserLog findByUserPhoneNumberAndStoreCodeOrderByHistoryNumDesc(String phoneNumber, int storeCode);

    List<UserLog> findByUserPhoneNumber(String phoneNumber);

    List<UserLog> findByStoreCode(int storeCode);
}
