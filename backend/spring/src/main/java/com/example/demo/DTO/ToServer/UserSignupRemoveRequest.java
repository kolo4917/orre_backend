package com.example.demo.DTO.ToServer;

public class UserSignupRemoveRequest {
    private String phoneNumber;
    private String password;
    private String username;

    public UserSignupRemoveRequest(String phoneNumber, String password, String username) {
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.username = username;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
