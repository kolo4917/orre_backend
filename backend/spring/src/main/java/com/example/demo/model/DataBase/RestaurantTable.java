package com.example.demo.model.DataBase;

import java.io.Serializable;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.IdClass;

@Entity
@Table(name = "restaurant_table")
@IdClass(RestaurantTableKey.class) // 복합 기본 키 클래스를 지정합니다.
public class RestaurantTable {

    @Id
    @Column(name = "store_code")
    private Integer storeCode;

    @Id
    @Column(name = "table_number")
    private Integer tableNumber;

    @Column(name = "table_person_number")
    private Integer tablePersonNumber;

    @Column(name = "table_available")
    private Integer tableAvailable;

    @Column(name = "table_user_phone_number")
    private String tablePhoneNumber;

    public RestaurantTable() {}

    public Integer getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(Integer storeCode) {
        this.storeCode = storeCode;
    }

    public Integer getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(Integer tableNumber) {
        this.tableNumber = tableNumber;
    }

    public Integer getTablePersonNumber() {
        return tablePersonNumber;
    }

    public void setTablePersonNumber(Integer tablePersonNumber) {
        this.tablePersonNumber = tablePersonNumber;
    }

    public Integer getTableAvailable() {
        return tableAvailable;
    }

    public void setTableAvailable(Integer tableAvailable) {
        this.tableAvailable = tableAvailable;
    }

    public String getTablePhoneNumber() {
        return tablePhoneNumber;
    }

    public void setTablePhoneNumber(String tablePhoneNumber) {
        this.tablePhoneNumber = tablePhoneNumber;
    }
}

// 복합 기본 키 클래스를 정의합니다.
class RestaurantTableKey implements Serializable {
    private Integer storeCode;
    private Integer tableNumber;

    public RestaurantTableKey() {}

    public RestaurantTableKey(Integer storeCode, Integer tableNumber) {
        this.storeCode = storeCode;
        this.tableNumber = tableNumber;
    }

    public Integer getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(Integer storeCode) {
        this.storeCode = storeCode;
    }

    public Integer getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(Integer tableNumber) {
        this.tableNumber = tableNumber;
    }

    // equals() 및 hashCode() 메서드 오버라이드
}
