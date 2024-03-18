package com.example.demo.DTO.ToServer;

public class AdminLoginRequest {
    private String adminPhoneNumber;
    private String adminPassword;


    public AdminLoginRequest(String adminPhoneNumber, String adminPassword) {
        this.adminPhoneNumber = adminPhoneNumber;
        this.adminPassword = adminPassword;
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
}
