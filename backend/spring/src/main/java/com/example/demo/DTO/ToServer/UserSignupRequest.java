package com.example.demo.DTO.ToServer;

public class UserSignupRequest {


    private String phoneNumber;
    private String verificationCode;
    private String password;
    private String username;

    public UserSignupRequest(String phoneNumber, String verificationCode, String password,String username) {
        this.phoneNumber = phoneNumber;
        this.verificationCode = verificationCode;
        this.password = password;
        this.username = username;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
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
