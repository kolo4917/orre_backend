package com.example.demo.DTO.ToServer;

public class UserPhoneNumberRequest {
    private String userPhoneNumber;
    public UserPhoneNumberRequest() {
    }

    public UserPhoneNumberRequest(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }
}
