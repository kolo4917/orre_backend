package com.example.demo.DTO.ToServer;

public class UserStoreWaitRequest {

    private String phoneNumber;
    private Integer storeCode;
    private Integer personNumber;

    public UserStoreWaitRequest(String phoneNumber, Integer storeCode, Integer personNumber) {
        this.phoneNumber = phoneNumber;
        this.storeCode = storeCode;
        this.personNumber = personNumber;
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

