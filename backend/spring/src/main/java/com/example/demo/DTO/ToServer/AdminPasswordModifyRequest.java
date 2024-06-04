package com.example.demo.DTO.ToServer;

public class AdminPasswordModifyRequest {
    private String adminPhoneNumber;
    private String verificationCode;
    private String newAdminPassword;

    public AdminPasswordModifyRequest(String adminPhoneNumber, String verificationCode, String newAdminPassword) {
        this.adminPhoneNumber = adminPhoneNumber;
        this.verificationCode = verificationCode;
        this.newAdminPassword = newAdminPassword;
    }

    public String getAdminPhoneNumber() {
        return adminPhoneNumber;
    }

    public void setAdminPhoneNumber(String adminPhoneNumber) {
        this.adminPhoneNumber = adminPhoneNumber;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public String getNewAdminPassword() {
        return newAdminPassword;
    }

    public void setNewAdminPassword(String newAdminPassword) {
        this.newAdminPassword = newAdminPassword;
    }
}
