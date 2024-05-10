package com.example.demo.repository;

import com.example.demo.model.DataBase.MenuInfo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface MenuInfoRepository extends JpaRepository<MenuInfo, Integer> {
    @Query("SELECT m.menu, m.price, m.menuCode ,m.available ,m.recommend, m.img, m.introduce FROM MenuInfo m WHERE m.storeCode = :storeCode AND m.tableNumber = 1")
    List<Object[]> findMenuDetailsByStoreCodeAndTableNumber(@Param("storeCode") Integer storeCode);
    MenuInfo findByStoreCodeAndMenuCode(Integer storeCode, String menuCode);
    // StoreCode로 해당 가게의 모든 메뉴 정보를 조회합니다.
    // StoreCode와 MenuCode의 Prefix로 메뉴 그룹에 속하는 모든 메뉴 정보를 조회합니다.
    @Transactional
    @Modifying
    @Query("UPDATE MenuInfo m SET m.available = :available WHERE m.storeCode = :storeCode AND m.menuCode LIKE CONCAT(:menuCodePrefix, '%')")
    void updateAvailableByStoreCodeAndMenuCodeStartingWith(@Param("storeCode") Integer storeCode, @Param("menuCodePrefix") String menuCodePrefix, @Param("available") Integer availableCode);

    @Transactional
    @Modifying
    @Query("UPDATE MenuInfo m SET m.available = :available WHERE m.storeCode = :storeCode AND m.menuCode = :menuCode")
    void updateAvailableByStoreCodeAndMenuCode(@Param("storeCode") Integer storeCode, @Param("menuCode") String menuCode, @Param("available") Integer availableCode);


    @Transactional
    @Modifying
    @Query("UPDATE MenuInfo m SET m.available = :available WHERE m.storeCode = :storeCode")
    void updateAvailableByStoreCode(@Param("storeCode") Integer storeCode, @Param("available") Integer availableCode);
    @Transactional
    @Modifying
    @Query("UPDATE MenuInfo m SET m.amount = m.amount + :amountToAdd " +
            "WHERE m.storeCode = :storeCode AND m.tableNumber = :tableNumber AND m.menuCode = :menuCode")
    void updateAmountByStoreCodeAndTableNumberAndMenuCode(@Param("storeCode") int storeCode,
                                                          @Param("tableNumber") int tableNumber,
                                                          @Param("menuCode") String menuCode,
                                                          @Param("amountToAdd") int amountToAdd);

    @Query("SELECT m.menu, m.price, m.amount " +
            "FROM MenuInfo m " +
            "WHERE m.storeCode = :storeCode AND m.tableNumber = :tableNumber AND m.amount > :amount")
    List<Object[]> findMenuDetailsByStoreCodeAndTableNumber(@Param("storeCode") Integer storeCode, @Param("tableNumber") Integer tableNumber, @Param("amount") Integer amount);

    // 특정 가게 코드에 대한 테이블 번호를 조회하는 메서드
    @Query("SELECT DISTINCT m.tableNumber FROM MenuInfo m WHERE m.storeCode = ?1")
    List<Integer> findTableNumbersByStoreCode(Integer storeCode);
    // 특정 가게의 테이블을 전부 가져오는 메서드

    List<MenuInfo> findByStoreCodeAndMenuAndMenuCode(Integer storeCode, String menu, String menuCode);

    List<MenuInfo> findByStoreCodeAndTableNumber(Integer storeCode, Integer tableNumber);




}
