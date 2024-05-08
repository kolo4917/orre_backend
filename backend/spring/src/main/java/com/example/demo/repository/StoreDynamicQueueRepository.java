package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import com.example.demo.model.DataBase.UserStoreWait;


@Repository
public interface StoreDynamicQueueRepository extends JpaRepository<UserStoreWait, String> {
    @Query("SELECT uw.storeCode, uw.waiting, uw.status FROM UserStoreWait uw WHERE uw.storeCode = :storeCode AND uw.status = 1 ORDER BY uw.waiting ASC")
    List<Object[]> findStoreDynamicQueue(@Param("storeCode") Integer storeCode);

    @Query("SELECT uw.storeCode, uw.waiting, uw.status, uw.userPhoneNumber, uw.personNumber FROM UserStoreWait uw WHERE uw.storeCode = :storeCode AND uw.status = 1 ORDER BY uw.waiting ASC")
    List<Object[]> findStoreByExtendedQueue(@Param("storeCode") Integer storeCode);

}

