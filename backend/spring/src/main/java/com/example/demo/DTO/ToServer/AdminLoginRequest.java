package com.example.demo.DTO.ToServer;

public class AdminLoginRequest {
    private String adminphonenumber;
    private String adminpassword;

    public AdminLoginRequest(String adminphonenumber, String adminpassword) {
        this.adminphonenumber = adminphonenumber;
        this.adminpassword = adminpassword;
    }

    public String getAdminPhoneNumber() {
        return adminphonenumber;
    }

    public void setAdminPhoneNumber(String adminphonenumber) {
        this.adminphonenumber = adminphonenumber;
    }

    public String getAdminPassword() {
        return adminpassword;
    }

    public void setAdminPassword(String adminpassword) {
        this.adminpassword = adminpassword;
    }
}
