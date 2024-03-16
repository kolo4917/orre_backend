package com.example.demo.repository;

import com.example.demo.model.DataBase.RestaurantTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface RestaurantTableRepository extends JpaRepository<RestaurantTable, Integer> {

    @Query(value = "SELECT rt.tableNumber, rt.tablePersonNumber, rt.tableAvailable FROM RestaurantTable rt WHERE rt.storeCode = :storeCode")
    List<Object[]> findAvailableTablesByStoreCode(@Param("storeCode") Integer storeCode);
}
