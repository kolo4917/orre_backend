package com.example.demo.DTO.ToServer;

public class StoreMenuOrderedCheckRequest {
    private int storeCode;
    private int tableNumber;

    public StoreMenuOrderedCheckRequest(int storeCode, int tableNumber) {
        this.storeCode = storeCode;
        this.tableNumber = tableNumber;
    }

    public int getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(int storeCode) {
        this.storeCode = storeCode;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }
}
