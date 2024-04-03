package com.example.demo.repository;

import com.example.demo.model.DataBase.RestaurantTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TableLockingRepository extends JpaRepository<RestaurantTable, Integer> {
    RestaurantTable findByStoreCodeAndTableNumber(Integer storeCode, Integer tableNumber);
}
