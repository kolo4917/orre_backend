package com.example.demo.model.DataBase;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.Column;

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

    public Store() {}

    public Store(Integer storeCode, Integer storeWaitingAmount, Integer storeTableData, String storeName, Integer storeWaiting, Integer storeInfoVersion) {
        this.storeCode = storeCode;
        this.storeWaitingAmount = storeWaitingAmount;
        this.storeTableData = storeTableData;
        this.storeName = storeName;
        this.storeWaiting = storeWaiting;
        this.storeInfoVersion = storeInfoVersion;
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

}
