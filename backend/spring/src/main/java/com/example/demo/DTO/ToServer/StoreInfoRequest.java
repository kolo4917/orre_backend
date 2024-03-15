package com.example.demo.DTO.ToServer;

public class StoreInfoRequest {
    private int storeCode;
    private int storeTableNumber;

    public int getStoreTableNumber() {return storeTableNumber;}

    public void setStoreTableNumber(int storeTableNumber) {this.storeTableNumber = storeTableNumber;}

    public int getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(int storeCode) {
        this.storeCode = storeCode;
    }
}
