package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.example.demo.model.DataBase.UserStoreWait;

public interface UserStoreWaitRepository extends JpaRepository<UserStoreWait, Long> {

    @Query("SELECT u FROM UserStoreWait u WHERE u.phoneNumber = :phoneNumber AND u.storeCode = :storeCode")
    UserStoreWait findByPhoneNumberAndStoreCode(String phoneNumber, Integer storeCode);

    @Query("SELECT MAX(u.waiting) FROM UserStoreWait u WHERE u.storeCode = :storeCode")
    Integer findMaxWaitingByStoreCode(Integer storeCode);

    UserStoreWait findByWaiting(Integer waiting);

    // 추가할 메소드
    @Query("SELECT u FROM UserStoreWait u WHERE u.storeCode = :storeCode AND u.waiting = :waitingNumber")
    UserStoreWait findByStoreCodeAndWaitingNumber(@Param("storeCode") Integer storeCode, @Param("waitingNumber") Integer waitingNumber);

}

