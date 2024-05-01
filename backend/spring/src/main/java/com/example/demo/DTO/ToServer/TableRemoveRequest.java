package com.example.demo.DTO.ToServer;

public class TableRemoveRequest {
    private int storeCode;
    private int storeRemoveTableNumber;
    private String jwtAdmin;

    public TableRemoveRequest(int storeCode, int storeRemoveTableNumber, String jwtAdmin) {
        this.storeCode = storeCode;
        this.storeRemoveTableNumber = storeRemoveTableNumber;
        this.jwtAdmin = jwtAdmin;
    }

    public int getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(int storeCode) {
        this.storeCode = storeCode;
    }

    public int getStoreRemoveTableNumber() {
        return storeRemoveTableNumber;
    }

    public void setStoreRemoveTableNumber(int storeAddTableNumber) {
        this.storeRemoveTableNumber = storeRemoveTableNumber;
    }

    public String getJwtAdmin() {
        return jwtAdmin;
    }

    public void setJwtAdmin(String jwtAdmin) {
        this.jwtAdmin = jwtAdmin;
    }
}
