package com.example.demo.DTO.ToServer;

public class UserStoreWaitRequest {

    private String userPhoneNumber;
    private Integer storeCode;
    private Integer personNumber;

    public UserStoreWaitRequest(String userPhoneNumber, Integer storeCode, Integer personNumber) {
        this.userPhoneNumber = userPhoneNumber;
        this.storeCode = storeCode;
        this.personNumber = personNumber;
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }

    public Integer getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(Integer storeCode) {
        this.storeCode = storeCode;
    }

    public Integer getPersonNumber() {
        return personNumber;
    }

    public void setPersonNumber(Integer personNumber) {
        this.personNumber = personNumber;
    }
}