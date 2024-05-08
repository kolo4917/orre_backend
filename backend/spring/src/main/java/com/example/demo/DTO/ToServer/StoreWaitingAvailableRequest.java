package com.example.demo.DTO.ToServer;

public class StoreWaitingAvailableRequest {
    private int storeCode;
    private int storeWaitingAvailable; // 1 불가, 0 가능
    private String jwtAdmin;

    public StoreWaitingAvailableRequest(int storeCode, int storeWaitingAvailable, String jwtAdmin) {
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

    public String getJwtAdmin() {
        return jwtAdmin;
    }

    public void setJwtAdmin(String jwtAdmin) {
        this.jwtAdmin = jwtAdmin;
    }
}
