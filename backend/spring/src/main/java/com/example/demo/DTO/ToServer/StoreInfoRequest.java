package com.example.demo.DTO.ToServer;

public class StoreInfoRequest {
    private int storeCode;
    private int tableNumber;

    public int getStoreTableNumber() {return tableNumber;}

    public void setStoreTableNumber(int tableNumber) {this.tableNumber = tableNumber;}

    public int getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(int storeCode) {
        this.storeCode = storeCode;
    }
}
