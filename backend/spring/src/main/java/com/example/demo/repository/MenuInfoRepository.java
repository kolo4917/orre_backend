package com.example.demo.repository;

import com.example.demo.model.DataBase.MenuInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface MenuInfoRepository extends JpaRepository<MenuInfo, Integer> {
    @Query("SELECT m.menu, m.price, m.recommend, m.img, m.introduce FROM MenuInfo m WHERE m.storeCode = :storeCode AND m.tableNumber = 1")
    List<Object[]> findMenuDetailsByStoreCodeAndTableNumber(@Param("storeCode") Integer storeCode);
}
