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


    public String getPhoneNumber() {
        return userPhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.userPhoneNumber = phoneNumber;
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

