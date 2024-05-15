package com.example.demo.DTO.ToServer;

public class UserLoginRequest {

    private String userPhoneNumber;
    private String userPassword;
    private String userFcmToken;

    public UserLoginRequest(String userPhoneNumber, String userPassword, String userFcmToken) {
        this.userPhoneNumber = userPhoneNumber;
        this.userPassword = userPassword;
        this.userFcmToken = userFcmToken;
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

    public String getUserFcmToken() {
        return userFcmToken;
    }

    public void setUserFcmToken(String userFcmToken) {
        this.userFcmToken = userFcmToken;
    }
}
