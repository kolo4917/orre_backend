package com.example.demo.repository;

import com.example.demo.model.DataBase.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface StoreRepository extends JpaRepository<Store, Integer> {
    @Query("SELECT s.storeCode, s.storeName, s.storeWaitingAmount, s.storeInfoVersion, s.storeImageMain FROM Store s WHERE s.storeCode = :storeCode")
    Object[] findStoreDetailsByStoreCode(@Param("storeCode") Integer storeCode);
}
