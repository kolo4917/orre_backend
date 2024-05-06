package com.example.demo.repository;

import com.example.demo.model.DataBase.StoreInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


@Repository
public interface StoreInfoRepository extends JpaRepository<StoreInfo, Long> {
    @Query("SELECT s.storeName, s.latitude, s.longitude, s.address FROM StoreInfo s WHERE s.storeCode = :storeCode")
    List<Object[]> findLocationDetailsByStoreCode(Integer storeCode);
}
