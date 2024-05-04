package com.example.demo.DTO.ToServer;

public class UserSignupRequest {


    private String userPhoneNumber;
    private String verificationCode;
    private String userPassword;
    private String username;

    public UserSignupRequest(String userPhoneNumber, String verificationCode, String userPassword, String username) {
        this.userPhoneNumber = userPhoneNumber;
        this.verificationCode = verificationCode;
        this.userPassword = userPassword;
        this.username = username;
    }

    public String getPhoneNumber() {
        return userPhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
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
