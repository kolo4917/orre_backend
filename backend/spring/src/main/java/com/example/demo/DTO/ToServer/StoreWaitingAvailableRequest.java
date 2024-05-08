package com.example.demo.DTO.ToServer;

public class StoreWaitingAvailableRequest {
    private int storeCode;
    private int storeWaitingAvailable;
    private int jwtAdmin;

    public StoreWaitingAvailableRequest(int storeCode, int storeWaitingAvailable, int jwtAdmin) {
        this.storeCode = storeCode;
        this.storeWaitingAvailable = storeWaitingAvailable;
        this.jwtAdmin = jwtAdmin;
    }

    public int getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(int storeCode) {
        this.storeCode = storeCode;
    }

    public int getStoreWaitingAvailable() {
        return storeWaitingAvailable;
    }

    public void setStoreWaitingAvailable(int storeWaitingAvailable) {
        this.storeWaitingAvailable = storeWaitingAvailable;
    }

    public int getJwtAdmin() {
        return jwtAdmin;
    }

    public void setJwtAdmin(int jwtAdmin) {
        this.jwtAdmin = jwtAdmin;
    }
}
