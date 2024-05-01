package com.example.demo.repository;

import com.example.demo.model.DataBase.RestaurantTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface RestaurantTableRepository extends JpaRepository<RestaurantTable, Integer> {

    //@Query 어노테이션을 사용한 사용자 정의 쿼리:
    @Query(value = "SELECT rt.tableNumber, rt.tablePersonNumber, rt.tableAvailable FROM RestaurantTable rt WHERE rt.storeCode = :storeCode")
    List<Object[]> findAvailableTablesByStoreCode(@Param("storeCode") Integer storeCode);

    //메서드 이름으로 쿼리 생성:
    RestaurantTable findByStoreCodeAndTableNumber(Integer storeCode, Integer tableNumber);



}
