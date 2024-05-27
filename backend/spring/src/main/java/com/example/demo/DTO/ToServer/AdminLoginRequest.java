package com.example.demo.DTO.ToServer;

public class AdminLoginRequest {
    private String adminPhoneNumber;
    private String adminPassword;
    private String adminFcmToken;

    public AdminLoginRequest(String adminPhoneNumber, String adminPassword, String adminFcmToken) {
        this.adminPhoneNumber = adminPhoneNumber;
        this.adminPassword = adminPassword;
        this.adminFcmToken = adminFcmToken;
    }

    public String getAdminPhoneNumber() {
        return adminPhoneNumber;
    }

    public void setAdminPhoneNumber(String adminPhoneNumber) {
        this.adminPhoneNumber = adminPhoneNumber;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public String getAdminFcmToken() {
        return adminFcmToken;
    }

    public void setAdminFcmToken(String adminFcmToken) {
        this.adminFcmToken = adminFcmToken;
    }
}
