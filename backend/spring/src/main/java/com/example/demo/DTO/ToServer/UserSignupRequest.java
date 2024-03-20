package com.example.demo.DTO.ToServer;

public class UserSignupRequest {


    private String phoneNumber;
    private String verificationCode;
    private String password;

    public UserSignupRequest(String phoneNumber, String verificationCode, String password) {
        this.phoneNumber = phoneNumber;
        this.verificationCode = verificationCode;
        this.password = password;
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
}
