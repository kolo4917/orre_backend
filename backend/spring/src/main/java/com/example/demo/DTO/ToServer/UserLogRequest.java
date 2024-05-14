package com.example.demo.DTO.ToServer;

public class UserLogRequest {
    private String userPhoneNumber;

    public UserLogRequest(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }
    public UserLogRequest() {
    }


    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }
}
