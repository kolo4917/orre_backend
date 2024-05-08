package com.example.demo.repository;

import com.example.demo.model.DataBase.MenuInfo;
import com.example.demo.model.DataBase.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface StoreRepository extends JpaRepository<Store, Integer> {
    @Query("SELECT s.storeCode, s.storeName, s.storePhoneNumber, s.storeWaitingAmount, s.storeWaitingAvailable, s.storeInfoVersion, s.storeImageMain, s.storeIntroduce, s.storeCategory, s.openingTime, s.closingTime, s.lastOrderTime, s.startBreakTime, s.endBreakTime FROM Store s WHERE s.storeCode = :storeCode")
    Object[] findStoreDetailsByStoreCode(@Param("storeCode") Integer storeCode);

    Store findByStoreCode(Integer storeCode);

}
