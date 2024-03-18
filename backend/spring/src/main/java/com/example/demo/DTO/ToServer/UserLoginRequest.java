package com.example.demo.DTO.ToServer;

public class UserLoginRequest {

    private String userPhoneNumber;
    private String userPassword;
    public UserLoginRequest(String userPhoneNumber, String userPassword) {
        this.userPhoneNumber = userPhoneNumber;
        this.userPassword = userPassword;
    }
    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
