package com.example.demo.model.DataBase;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.Column;

import java.time.LocalTime;

@Entity
@Table(name = "store")
public class Store {

    @Id
    @Column(name = "store_code")
    private Integer storeCode;

    @Column(name = "store_waiting_amount")
    private Integer storeWaitingAmount;

    @Column(name = "store_table_data")
    private Integer storeTableData;

    @Column(name = "store_name")
    private String storeName;

    @Column(name = "store_waiting")
    private Integer storeWaiting;

    @Column(name = "store_info_version")
    private Integer storeInfoVersion;
    @Column(name = "store_image_main")
    private String storeImageMain;
    @Column(name = "store_introduce")
    private String storeIntroduce;
    @Column(name = "store_category")
    private String storeCategory;
    @Column(name = "store_opening_time")
    private LocalTime openingTime;
    @Column(name = "store_closing_time")
    private LocalTime closingTime;
    @Column(name = "store_last_order_time")
    private LocalTime lastOrderTime;

    public Store() {}

    public Store(Integer storeCode, Integer storeWaitingAmount, Integer storeTableData, String storeName,
                 Integer storeWaiting, Integer storeInfoVersion, String storeImageMain, String storeIntroduce, String storeCategory,
                 LocalTime openingTime, LocalTime closingTime, LocalTime lastOrderTime) {
        this.storeCode = storeCode;
        this.storeWaitingAmount = storeWaitingAmount;
        this.storeTableData = storeTableData;
        this.storeName = storeName;
        this.storeWaiting = storeWaiting;
        this.storeInfoVersion = storeInfoVersion;
        this.storeImageMain = storeImageMain;
        this.storeIntroduce = storeIntroduce;
        this.storeCategory = storeCategory;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
        this.lastOrderTime = lastOrderTime;
    }

    public Integer getStoreCode() { return storeCode; }
    public void setStoreCode(Integer storeCode) { this.storeCode = storeCode; }

    public Integer getStoreWaitingAmount() { return storeWaitingAmount; }
    public void setStoreWaitingAmount(Integer storeWaitingAmount) { this.storeWaitingAmount = storeWaitingAmount; }

    public Integer getStoreTableData() { return storeTableData; }
    public void setStoreTableData(Integer storeTableData) { this.storeTableData = storeTableData; }

    public String getStoreName() { return storeName; }
    public void setStoreName(String storeName) { this.storeName = storeName; }

    public Integer getStoreWaiting() { return storeWaiting; }
    public void setStoreWaiting(Integer storeWaiting) { this.storeWaiting = storeWaiting; }

    public Integer getStoreInfoVersion() { return storeInfoVersion; }
    public void setStoreInfoVersion(Integer storeInfoVersion) { this.storeInfoVersion = storeInfoVersion; }

    public String getStoreImageMain() {return storeImageMain;}

    public void setStoreImageMain(String storeImageMain) {this.storeImageMain = storeImageMain;}

    public String getStoreIntroduce() {return storeIntroduce;}

    public void setStoreIntroduce(String storeIntroduce) {this.storeIntroduce = storeIntroduce;}

    public String getStoreCategory() {return storeCategory;}

    public void setStoreCategory(String storeCategory) {this.storeCategory = storeCategory;}

    public LocalTime getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(LocalTime openingTime) {
        this.openingTime = openingTime;
    }

    public LocalTime getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(LocalTime closingTime) {
        this.closingTime = closingTime;
    }

    public LocalTime getLastOrderTime() {
        return lastOrderTime;
    }

    public void setLastOrderTime(LocalTime lastOrderTime) {
        this.lastOrderTime = lastOrderTime;
    }
}
