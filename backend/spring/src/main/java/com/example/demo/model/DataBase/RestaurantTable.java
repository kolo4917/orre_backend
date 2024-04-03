package com.example.demo.model.DataBase;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.OneToOne;
import jakarta.persistence.JoinColumn;

@Entity
@Table(name = "restaurant_table")
public class RestaurantTable {

    @Id
    @Column(name = "store_code")
    private Integer storeCode;

    @Column(name = "table_number")
    private Integer tableNumber;

    @Column(name = "table_person_number")
    private Integer tablePersonNumber;

    @Column(name = "table_available")
    private Integer tableAvailable;
    @Column(name = "table_user_phone_number")
    private String tablePhoneNumber;

    @OneToOne
    @JoinColumn(name = "store_code")
    private Store store;

    public RestaurantTable() {}

    public RestaurantTable(Integer storeCode, Integer tableNumber, Integer tablePersonNumber, Integer tableAvailable, String tablePhoneNumber,Store store) {
        this.storeCode = storeCode;
        this.tableNumber = tableNumber;
        this.tablePersonNumber = tablePersonNumber;
        this.tableAvailable = tableAvailable;
        this.tablePhoneNumber = tablePhoneNumber;
        this.store = store;
    }

    public Integer getStoreCode() { return storeCode; }
    public void setStoreCode(Integer storeCode) { this.storeCode = storeCode; }

    public Integer getTableNumber() { return tableNumber; }
    public void setTableNumber(Integer tableNumber) { this.tableNumber = tableNumber; }

    public Integer getTablePersonNumber() { return tablePersonNumber; }
    public void setTablePersonNumber(Integer tablePersonNumber) { this.tablePersonNumber = tablePersonNumber; }

    public Integer getTableAvailable() { return tableAvailable; }
    public void setTableAvailable(Integer tableAvailable) { this.tableAvailable = tableAvailable; }

    public String getTablePhoneNumber() {return tablePhoneNumber;}

    public void setTablePhoneNumber(String tablePhoneNumber) {this.tablePhoneNumber = tablePhoneNumber;}

    public Store getStore() { return store; }
    public void setStore(Store store) { this.store = store; }

}
