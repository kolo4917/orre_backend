package com.example.demo.DTO.ToServer;

public class TableAddRequest {
    private int storeCode;
    private int storeAddTableNumber;
    private int personNumber;
    private String jwtAdmin;

    public TableAddRequest(int storeCode, int storeAddTableNumber, int personNumber, String jwtAdmin) {
        this.storeCode = storeCode;
        this.storeAddTableNumber = storeAddTableNumber;
        this.personNumber = personNumber;
        this.jwtAdmin = jwtAdmin;
    }

    public int getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(int storeCode) {
        this.storeCode = storeCode;
    }

    public int getStoreAddTableNumber() {
        return storeAddTableNumber;
    }

    public void setStoreAddTableNumber(int storeAddTableNumber) {
        this.storeAddTableNumber = storeAddTableNumber;
    }

    public int getPersonNumber() {
        return personNumber;
    }

    public void setPersonNumber(int personNumber) {
        this.personNumber = personNumber;
    }

    public String getJwtAdmin() {
        return jwtAdmin;
    }

    public void setJwtAdmin(String jwtAdmin) {
        this.jwtAdmin = jwtAdmin;
    }
}
