package com.example.demo.repository;

import com.example.demo.model.DataBase.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

@Repository
public interface StoreDynamicQueueRepository extends JpaRepository<Store, Integer> {
    @Query("SELECT s.storeCode, s.storeWaitingAmount FROM Store s WHERE (:storeCode IS NULL OR s.storeCode = :storeCode)")
    List<Object[]> findStoreDynamicQueue(@Param("storeCode") Integer storeCode);
}
