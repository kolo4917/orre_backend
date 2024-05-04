package com.example.demo.DTO.ToServer;

public class UserSignupRemoveRequest {
    private String userPhoneNumber;
    private String userPassword;
    private String username;

    public UserSignupRemoveRequest(String userPhoneNumber, String userPassword, String username) {
        this.userPhoneNumber = userPhoneNumber;
        this.userPassword = userPassword;
        this.username = username;
    }

    public String getPhoneNumber() {
        return userPhoneNumber;
    }

    public void setPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }

    public String getPassword() {
        return userPassword;
    }

    public void setPassword(String password) {
        this.userPassword = userPassword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
